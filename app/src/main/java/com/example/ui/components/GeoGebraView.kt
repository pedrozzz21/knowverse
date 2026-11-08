package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.math.GeoGebraCommandParser
import com.example.data.math.GeoGebraCommandPrimitive
import com.example.data.math.GeoGebraGraphConfig
import com.example.data.math.GeoGebraPoint
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.util.Locale
import kotlin.math.*

/**
 * Estado reativo compartilhado entre o Card e o Modo de Tela Cheia do Laboratório Gráfico.
 * Preserva zoom, pan, expressão ativa, reta tangente, somas de Riemann e camadas analíticas.
 */
class MathGraphLabState(config: GeoGebraGraphConfig) {
    var activeExpression by mutableStateOf(config.functionExpression)
    var customInputText by mutableStateOf(config.functionExpression)
    var validationError by mutableStateOf<String?>(null)
    var isCustomizing by mutableStateOf(false)

    var curXMin by mutableDoubleStateOf(config.xMin)
    var curXMax by mutableDoubleStateOf(config.xMax)
    var curYMin by mutableDoubleStateOf(config.yMin)
    var curYMax by mutableDoubleStateOf(config.yMax)

    var tangentX by mutableDoubleStateOf(1.0.coerceIn(config.xMin + 0.5, config.xMax - 0.5))
    var inspectedX by mutableStateOf<Double?>(null)
    var isTraceDragMode by mutableStateOf(false)

    var showRiemann by mutableStateOf(config.showRiemannSum)
    var riemannPartitions by mutableIntStateOf(config.riemannPartitions.coerceIn(2, 60))
    var riemannType by mutableStateOf(RiemannType.MIDPOINT)

    var showRootsOverlay by mutableStateOf(config.showRoots)
    var showExtremaOverlay by mutableStateOf(config.showExtrema)
    var showInflectionOverlay by mutableStateOf(false)
    var showAsymptotesOverlay by mutableStateOf(config.showAsymptotes)

    fun resetToDefault(config: GeoGebraGraphConfig) {
        activeExpression = config.functionExpression
        customInputText = config.functionExpression
        validationError = null
        curXMin = config.xMin
        curXMax = config.xMax
        curYMin = config.yMin
        curYMax = config.yMax
        tangentX = 1.0.coerceIn(config.xMin + 0.5, config.xMax - 0.5)
        inspectedX = null
        showRiemann = config.showRiemannSum
        riemannPartitions = config.riemannPartitions.coerceIn(2, 60)
        riemannType = RiemannType.MIDPOINT
        showRootsOverlay = config.showRoots
        showExtremaOverlay = config.showExtrema
        showInflectionOverlay = false
        showAsymptotesOverlay = config.showAsymptotes
    }
}

/**
 * Visualizador Matemático Interativo Nativo do Knowverse (Camadas C, D, E e F).
 * Executa 100% offline em Jetpack Compose Canvas com AST compilada, suporte a geometria analítica
 * (polígonos, segmentos, circunferências, elipses, assíntotas), inspeção com snap magnético,
 * somas de Riemann nos 4 métodos clássicos e localização automática de pontos críticos.
 */
@Composable
fun GeoGebraMathGraphView(
    config: GeoGebraGraphConfig,
    modifier: Modifier = Modifier,
    isFullScreenMode: Boolean = false,
    onToggleFullScreen: (() -> Unit)? = null,
    sharedState: MathGraphLabState? = null
) {
    val labState = sharedState ?: remember(config.title, config.functionExpression) {
        MathGraphLabState(config)
    }
    var isFullScreenDialogVisible by remember { mutableStateOf(false) }

    // Primitivas geométricas extraídas de config.commands (cacheadas por config)
    val commandPrimitives = remember(config.commands, labState.activeExpression) {
        GeoGebraCommandParser.parse(config.commands, labState.activeExpression)
    }

    // Verifica se a expressão principal é apenas o eixo neutro "0*x" usado em figuras puramente geométricas
    val isPureGeometryScene = remember(labState.activeExpression, commandPrimitives) {
        val clean = labState.activeExpression.replace(" ", "")
        (clean == "0*x" || clean == "0") && commandPrimitives.any {
            it is GeoGebraCommandPrimitive.Polygon2D ||
                it is GeoGebraCommandPrimitive.Circle2D ||
                it is GeoGebraCommandPrimitive.Ellipse2D
        }
    }

    // Pontos críticos calculados dinamicamente sob demanda quando as camadas analíticas estão ativas
    val detectedRoots = remember(labState.activeExpression, labState.curXMin, labState.curXMax, labState.showRootsOverlay) {
        if (labState.showRootsOverlay && !isPureGeometryScene) {
            MathExpressionEvaluator.findRoots(labState.activeExpression, labState.curXMin, labState.curXMax)
        } else emptyList()
    }

    val detectedExtrema = remember(labState.activeExpression, labState.curXMin, labState.curXMax, labState.showExtremaOverlay) {
        if (labState.showExtremaOverlay && !isPureGeometryScene) {
            MathExpressionEvaluator.findExtrema(labState.activeExpression, labState.curXMin, labState.curXMax)
        } else emptyList()
    }

    val detectedInflections = remember(labState.activeExpression, labState.curXMin, labState.curXMax, labState.showInflectionOverlay) {
        if (labState.showInflectionOverlay && !isPureGeometryScene) {
            MathExpressionEvaluator.findInflectionPoints(labState.activeExpression, labState.curXMin, labState.curXMax)
        } else emptyList()
    }

    val detectedAsymptotes = remember(labState.activeExpression, labState.curXMin, labState.curXMax, labState.showAsymptotesOverlay) {
        if (labState.showAsymptotesOverlay && !isPureGeometryScene) {
            MathExpressionEvaluator.findVerticalAsymptotes(labState.activeExpression, labState.curXMin, labState.curXMax)
        } else emptyList()
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = modifier
            .fillMaxWidth()
            .testTag("math_graph_card")
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // 1. Header do Gráfico com identificador matemático e controles de topo
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF0F766E).copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ShowChart,
                            contentDescription = null,
                            tint = Color(0xFF2DD4BF),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = config.title,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = Color(0xFF1E3A8A).copy(alpha = 0.45f),
                                shape = RoundedCornerShape(4.dp),
                                border = androidx.compose.foundation.BorderStroke(0.6.dp, Color(0xFF60A5FA).copy(alpha = 0.5f))
                            ) {
                                Text(
                                    text = "Motor Matemático Nativo",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFF93C5FD),
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                )
                            }
                        }
                        if (!isPureGeometryScene) {
                            Text(
                                text = "f(x) = ${labState.activeExpression}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFF67E8F9),
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Alternar Modo Rastreamento Contínuo vs Pan
                    IconButton(
                        onClick = { labState.isTraceDragMode = !labState.isTraceDragMode },
                        modifier = Modifier
                            .size(32.dp)
                            .testTag("graph_trace_mode_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.TouchApp,
                            contentDescription = "Modo Rastrear Curva",
                            tint = if (labState.isTraceDragMode) Color(0xFF38BDF8) else TextSecondary,
                            modifier = Modifier.size(17.dp)
                        )
                    }

                    // Botão Personalizar Função
                    IconButton(
                        onClick = {
                            labState.isCustomizing = !labState.isCustomizing
                            if (labState.isCustomizing) {
                                labState.customInputText = labState.activeExpression
                                labState.validationError = null
                            }
                        },
                        modifier = Modifier
                            .size(32.dp)
                            .testTag("graph_customize_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Personalizar Função",
                            tint = if (labState.isCustomizing) GoldXp else TextSecondary,
                            modifier = Modifier.size(17.dp)
                        )
                    }

                    // Botão Redefinir Vista
                    IconButton(
                        onClick = { labState.resetToDefault(config) },
                        modifier = Modifier
                            .size(32.dp)
                            .testTag("graph_reset_top_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.RestartAlt,
                            contentDescription = "Redefinir Gráfico",
                            tint = TextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Botão Tela Cheia
                    if (!isFullScreenMode) {
                        IconButton(
                            onClick = {
                                if (onToggleFullScreen != null) {
                                    onToggleFullScreen()
                                } else {
                                    isFullScreenDialogVisible = true
                                }
                            },
                            modifier = Modifier
                                .size(32.dp)
                                .testTag("graph_fullscreen_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Fullscreen,
                                contentDescription = "Expandir Gráfico",
                                tint = TextSecondary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            // 2. Painel de entrada para digitar função customizada com validação sintática real
            AnimatedVisibility(visible = labState.isCustomizing) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(SurfaceElevated, RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Digite qualquer expressão matemática em x (ex: 2x^2 - 4, sin(x)/x, |x - 2|):",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GeoGebraCustomInputField(
                            valText = labState.customInputText,
                            onValChange = {
                                labState.customInputText = it
                                if (labState.validationError != null) {
                                    labState.validationError = null
                                }
                            },
                            placeholder = "ex: (x^2 - 4)/(x - 2) ou sin(x)/x",
                            isError = labState.validationError != null,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("graph_custom_input")
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                val candidate = labState.customInputText.trim()
                                when (val validation = MathExpressionEvaluator.validateExpression(candidate)) {
                                    is ExpressionValidationResult.Valid -> {
                                        labState.activeExpression = candidate
                                        labState.validationError = null
                                        labState.isCustomizing = false
                                        labState.inspectedX = null
                                    }
                                    is ExpressionValidationResult.Invalid -> {
                                        labState.validationError = validation.errorMessage
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .height(42.dp)
                                .testTag("graph_plot_submit_btn")
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = "Plotar", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Plotar", fontSize = 12.sp)
                        }
                    }

                    // Exibição clara de erro de sintaxe quando a expressão digitada é inválida
                    if (labState.validationError != null) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(ErrorRose.copy(alpha = 0.14f), RoundedCornerShape(6.dp))
                                .border(0.8.dp, ErrorRose.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 5.dp)
                                .testTag("graph_syntax_error_banner")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ErrorOutline,
                                contentDescription = null,
                                tint = ErrorRose,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = labState.validationError ?: "",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = ErrorRose,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }
            }

            // 3. Presets de Funções Relacionadas + Toggles de Análise Automática
            if (!isPureGeometryScene) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    config.presetFunctions.forEach { preset ->
                        val isSelected = labState.activeExpression == preset
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                labState.activeExpression = preset
                                labState.validationError = null
                                labState.inspectedX = null
                            },
                            label = {
                                Text(
                                    text = preset,
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = if (isSelected) GoldXp else TextPrimary
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = GoldXp.copy(alpha = 0.2f),
                                containerColor = SurfaceElevated
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = BorderSubtle,
                                selectedBorderColor = GoldXp
                            )
                        )
                    }

                    // Chips para ativar/desativar camadas de análise matemática em tempo real
                    AnalysisToggleChip(
                        label = "Raízes",
                        selected = labState.showRootsOverlay,
                        accentColor = SuccessEmerald,
                        onClick = { labState.showRootsOverlay = !labState.showRootsOverlay }
                    )
                    AnalysisToggleChip(
                        label = "Extremos",
                        selected = labState.showExtremaOverlay,
                        accentColor = GoldXp,
                        onClick = { labState.showExtremaOverlay = !labState.showExtremaOverlay }
                    )
                    AnalysisToggleChip(
                        label = "Inflexão",
                        selected = labState.showInflectionOverlay,
                        accentColor = Color(0xFFC084FC),
                        onClick = { labState.showInflectionOverlay = !labState.showInflectionOverlay }
                    )
                    AnalysisToggleChip(
                        label = "Assíntotas",
                        selected = labState.showAsymptotesOverlay,
                        accentColor = ErrorRose,
                        onClick = { labState.showAsymptotesOverlay = !labState.showAsymptotesOverlay }
                    )
                }
            }

            // 4. Controles de Derivada / Reta Tangente
            if (config.showTangentSlider && !isPureGeometryScene) {
                Surface(
                    color = SurfaceElevated,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                        val slope = MathExpressionEvaluator.derivative(labState.activeExpression, labState.tangentX)
                        val y0 = MathExpressionEvaluator.evaluate(labState.activeExpression, labState.tangentX)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Timeline,
                                    contentDescription = null,
                                    tint = GoldXp,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "x₀ = ${String.format(Locale.US, "%.2f", labState.tangentX)}  |  f(x₀) = ${if (y0.isNaN()) "Indef." else String.format(Locale.US, "%.2f", y0)}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = TextPrimary,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Monospace
                                    )
                                )
                            }
                            Text(
                                text = "f'(x₀) = ${if (slope.isNaN()) "Indef." else String.format(Locale.US, "%.3f", slope)}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = GoldXp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace
                                )
                            )
                        }
                        val sliderMin = (labState.curXMin + 0.1).toFloat()
                        val sliderMax = maxOf(sliderMin + 0.2f, (labState.curXMax - 0.1).toFloat())
                        Slider(
                            value = labState.tangentX.toFloat().coerceIn(sliderMin, sliderMax),
                            onValueChange = { newVal ->
                                labState.tangentX = newVal.toDouble()
                            },
                            valueRange = sliderMin..sliderMax,
                            colors = SliderDefaults.colors(
                                thumbColor = GoldXp,
                                activeTrackColor = GoldXp,
                                inactiveTrackColor = BorderSubtle
                            ),
                            modifier = Modifier.height(28.dp)
                        )
                    }
                }
            }

            // 5. Controles de Integração Definida e Somas de Riemann Completas
            if ((config.showAreaUnderCurve || config.showRiemannSum) && !isPureGeometryScene) {
                val integralVal = remember(labState.activeExpression, config.integralA, config.integralB) {
                    MathExpressionEvaluator.definiteIntegral(
                        labState.activeExpression,
                        config.integralA,
                        config.integralB
                    )
                }
                val riemannVal = remember(
                    labState.activeExpression,
                    config.integralA,
                    config.integralB,
                    labState.riemannPartitions,
                    labState.riemannType
                ) {
                    MathExpressionEvaluator.calculateRiemannSum(
                        labState.activeExpression,
                        config.integralA,
                        config.integralB,
                        labState.riemannPartitions,
                        labState.riemannType
                    )
                }

                Surface(
                    color = SurfaceElevated.copy(alpha = 0.85f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Functions,
                                    contentDescription = null,
                                    tint = Color(0xFF38BDF8),
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "∫ [${config.integralA}, ${config.integralB}] = ${if (integralVal.isNaN()) "Divergente/Indef." else String.format(Locale.US, "%.4f", integralVal)}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFF38BDF8),
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Monospace
                                    )
                                )
                            }
                            Surface(
                                color = if (labState.showRiemann) GoldXp.copy(alpha = 0.18f) else Color.Transparent,
                                shape = RoundedCornerShape(6.dp),
                                border = androidx.compose.foundation.BorderStroke(
                                    0.8.dp,
                                    if (labState.showRiemann) GoldXp else BorderSubtle
                                ),
                                modifier = Modifier
                                    .clickable { labState.showRiemann = !labState.showRiemann }
                                    .testTag("riemann_toggle_btn")
                            ) {
                                Text(
                                    text = if (labState.showRiemann) "Riemann Ativo (n=${labState.riemannPartitions})" else "Ver Soma de Riemann",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = if (labState.showRiemann) GoldXp else TextSecondary,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }

                        // Painel expandido de Somas de Riemann (4 métodos + slider de partições n)
                        AnimatedVisibility(visible = labState.showRiemann) {
                            Column(modifier = Modifier.padding(top = 6.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${labState.riemannType.label}: ${if (riemannVal.isNaN()) "Indef." else String.format(Locale.US, "%.4f", riemannVal)}",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = GoldXp,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.Monospace
                                        )
                                    )
                                    if (!integralVal.isNaN() && !riemannVal.isNaN()) {
                                        val absErr = abs(integralVal - riemannVal)
                                        Text(
                                            text = "Erro |Δ| = ${String.format(Locale.US, "%.4f", absErr)}",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = TextSecondary,
                                                fontFamily = FontFamily.Monospace,
                                                fontSize = 10.sp
                                            )
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                // Seletor dos 4 métodos de Riemann
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .horizontalScroll(rememberScrollState()),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    RiemannType.entries.forEach { rType ->
                                        val isTypeSelected = labState.riemannType == rType
                                        Surface(
                                            color = if (isTypeSelected) GoldXp.copy(alpha = 0.22f) else ObsidianSurface,
                                            shape = RoundedCornerShape(6.dp),
                                            border = androidx.compose.foundation.BorderStroke(
                                                0.8.dp,
                                                if (isTypeSelected) GoldXp else BorderSubtle
                                            ),
                                            modifier = Modifier.clickable { labState.riemannType = rType }
                                        ) {
                                            Text(
                                                text = rType.label,
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    color = if (isTypeSelected) GoldXp else TextSecondary,
                                                    fontSize = 10.sp,
                                                    fontWeight = if (isTypeSelected) FontWeight.Bold else FontWeight.Normal
                                                ),
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                    }
                                }

                                // Slider de partições n (2 a 60)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Partições n = ${labState.riemannPartitions}",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TextSecondary,
                                            fontFamily = FontFamily.Monospace,
                                            fontSize = 10.sp
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Slider(
                                        value = labState.riemannPartitions.toFloat(),
                                        onValueChange = { labState.riemannPartitions = it.roundToInt().coerceIn(2, 60) },
                                        valueRange = 2f..60f,
                                        colors = SliderDefaults.colors(
                                            thumbColor = GoldXp,
                                            activeTrackColor = GoldXp,
                                            inactiveTrackColor = BorderSubtle
                                        ),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(24.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // 6. Container do Visualizador Matemático Nativo
            val graphModifier = if (isFullScreenMode) {
                Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false)
                    .height(520.dp)
            } else {
                Modifier
                    .fillMaxWidth()
                    .height(265.dp)
            }

            Box(
                modifier = graphModifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF090D16))
                    .border(1.dp, BorderSubtle.copy(alpha = 0.8f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                NativeMathGraphCanvas(
                    config = config,
                    activeExpression = labState.activeExpression,
                    isPureGeometryScene = isPureGeometryScene,
                    commandPrimitives = commandPrimitives,
                    detectedRoots = detectedRoots,
                    detectedExtrema = detectedExtrema,
                    detectedInflections = detectedInflections,
                    detectedAsymptotes = detectedAsymptotes,
                    tangentX = labState.tangentX,
                    curXMin = labState.curXMin,
                    curXMax = labState.curXMax,
                    curYMin = labState.curYMin,
                    curYMax = labState.curYMax,
                    inspectedX = labState.inspectedX,
                    isTraceDragMode = labState.isTraceDragMode,
                    showRiemannSum = labState.showRiemann,
                    riemannPartitions = labState.riemannPartitions,
                    riemannType = labState.riemannType,
                    onBoundsChange = { nXMin, nXMax, nYMin, nYMax ->
                        labState.curXMin = nXMin
                        labState.curXMax = nXMax
                        labState.curYMin = nYMin
                        labState.curYMax = nYMax
                    },
                    onInspectChange = { labState.inspectedX = it },
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("native_math_canvas")
                )

                // Tooltip de inspeção flutuante com coordenadas e derivada instantânea
                if (labState.inspectedX != null && !isPureGeometryScene) {
                    val ix = labState.inspectedX!!
                    val iy = MathExpressionEvaluator.evaluate(labState.activeExpression, ix)
                    val iSlope = MathExpressionEvaluator.derivative(labState.activeExpression, ix)
                    Surface(
                        color = Color(0xEE0F172A),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF38BDF8)),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                            .testTag("graph_inspect_tooltip")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF38BDF8))
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = buildString {
                                    append("x = ${String.format(Locale.US, "%.3f", ix)}")
                                    append("  |  y = ${if (iy.isNaN()) "Indef." else String.format(Locale.US, "%.3f", iy)}")
                                    if (!iSlope.isNaN()) {
                                        append("  |  f'(x) = ${String.format(Locale.US, "%.2f", iSlope)}")
                                    }
                                },
                                color = Color(0xFFF8FAFC),
                                fontFamily = FontFamily.Monospace,
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Toolbar flutuante com botões de Zoom e Reset (Canto Superior Direito)
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Surface(
                        color = Color(0xD91E293B),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x3394A3B8)),
                        modifier = Modifier
                            .size(28.dp)
                            .testTag("graph_zoom_in_btn")
                            .clickable {
                                val xSpan = labState.curXMax - labState.curXMin
                                val ySpan = labState.curYMax - labState.curYMin
                                if (xSpan > 0.1 && ySpan > 0.1) {
                                    labState.curXMin += xSpan * 0.15
                                    labState.curXMax -= xSpan * 0.15
                                    labState.curYMin += ySpan * 0.15
                                    labState.curYMax -= ySpan * 0.15
                                }
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Add, contentDescription = "Zoom In", tint = Color(0xFF94A3B8), modifier = Modifier.size(14.dp))
                        }
                    }

                    Surface(
                        color = Color(0xD91E293B),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x3394A3B8)),
                        modifier = Modifier
                            .size(28.dp)
                            .testTag("graph_zoom_out_btn")
                            .clickable {
                                val xSpan = labState.curXMax - labState.curXMin
                                val ySpan = labState.curYMax - labState.curYMin
                                if (xSpan < 500.0 && ySpan < 500.0) {
                                    labState.curXMin -= xSpan * 0.2
                                    labState.curXMax += xSpan * 0.2
                                    labState.curYMin -= ySpan * 0.2
                                    labState.curYMax += ySpan * 0.2
                                }
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Remove, contentDescription = "Zoom Out", tint = Color(0xFF94A3B8), modifier = Modifier.size(14.dp))
                        }
                    }

                    Surface(
                        color = Color(0xD91E293B),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x3394A3B8)),
                        modifier = Modifier
                            .size(28.dp)
                            .testTag("graph_reset_canvas_btn")
                            .clickable {
                                labState.curXMin = config.xMin
                                labState.curXMax = config.xMax
                                labState.curYMin = config.yMin
                                labState.curYMax = config.yMax
                                labState.inspectedX = null
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.RestartAlt, contentDescription = "Reset", tint = Color(0xFF94A3B8), modifier = Modifier.size(14.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 7. Fundamentação Analítica, Insight Matemático e Legenda de Pontos
            Surface(
                color = SurfaceElevated.copy(alpha = 0.6f),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFF67E8F9),
                            modifier = Modifier
                                .size(16.dp)
                                .padding(top = 1.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Fundamentação Analítica:",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF67E8F9)
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = config.didacticExplanation,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextPrimary,
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp
                                )
                            )
                        }
                    }

                    // Exibe mathInsight se estiver configurado no preset/exercício
                    if (config.mathInsight.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            color = GoldXp.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(0.8.dp, GoldXp.copy(alpha = 0.35f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = null,
                                    tint = GoldXp,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = config.mathInsight,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GoldXp,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 11.sp
                                    )
                                )
                            }
                        }
                    }

                    if (config.analysisPoints.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            config.analysisPoints.forEach { pt ->
                                val chipColor = try {
                                    Color(android.graphics.Color.parseColor(pt.colorHex))
                                } catch (_: Throwable) {
                                    Color(0xFFEF4444)
                                }
                                Surface(
                                    color = ObsidianSurface,
                                    shape = RoundedCornerShape(6.dp),
                                    border = androidx.compose.foundation.BorderStroke(0.8.dp, BorderSubtle),
                                    modifier = Modifier.clickable {
                                        labState.inspectedX = pt.x
                                    }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .clip(CircleShape)
                                                .background(if (pt.isOpenCircle) Color.Transparent else chipColor)
                                                .border(1.5.dp, chipColor, CircleShape)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "${pt.label}: (${pt.x}, ${pt.y}) ${if (pt.isOpenCircle) "[Buraco/Não Definida]" else ""}",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = TextSecondary,
                                                fontSize = 11.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // 8. Diálogo Modal de Tela Cheia compartilhando o mesmo estado (labState)
    if (isFullScreenDialogVisible) {
        Dialog(
            onDismissRequest = { isFullScreenDialogVisible = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF090D16)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ObsidianSurface)
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ShowChart,
                                contentDescription = null,
                                tint = Color(0xFF2DD4BF),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Laboratório de Gráficos: ${config.title}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            )
                        }
                        IconButton(onClick = { isFullScreenDialogVisible = false }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Fechar",
                                tint = TextPrimary
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        GeoGebraMathGraphView(
                            config = config,
                            isFullScreenMode = true,
                            onToggleFullScreen = { isFullScreenDialogVisible = false },
                            sharedState = labState
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AnalysisToggleChip(
    label: String,
    selected: Boolean,
    accentColor: Color,
    onClick: () -> Unit
) {
    Surface(
        color = if (selected) accentColor.copy(alpha = 0.2f) else SurfaceElevated,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(
            0.8.dp,
            if (selected) accentColor else BorderSubtle
        ),
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = if (selected) accentColor else TextSecondary,
                fontSize = 10.5.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun GeoGebraCustomInputField(
    valText: String,
    onValChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valText,
        onValueChange = onValChange,
        placeholder = { Text(placeholder, fontSize = 12.sp, color = TextSecondary) },
        singleLine = true,
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (isError) ErrorRose else PrimaryViolet,
            unfocusedBorderColor = if (isError) ErrorRose else BorderSubtle,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.height(50.dp)
    )
}

/**
 * Canvas Nativo de Desenho Matemático com Escala Adaptativa, AST Compilada,
 * Primitivas de Geometria Analítica, Preenchimento Descontínuo Seguro e Rótulos Anti-Colisão.
 */
@Composable
fun NativeMathGraphCanvas(
    config: GeoGebraGraphConfig,
    activeExpression: String,
    isPureGeometryScene: Boolean = false,
    commandPrimitives: List<GeoGebraCommandPrimitive> = emptyList(),
    detectedRoots: List<Double> = emptyList(),
    detectedExtrema: List<MathCriticalPoint> = emptyList(),
    detectedInflections: List<MathCriticalPoint> = emptyList(),
    detectedAsymptotes: List<Double> = emptyList(),
    tangentX: Double,
    curXMin: Double,
    curXMax: Double,
    curYMin: Double,
    curYMax: Double,
    inspectedX: Double?,
    isTraceDragMode: Boolean = false,
    showRiemannSum: Boolean = false,
    riemannPartitions: Int = 8,
    riemannType: RiemannType = RiemannType.MIDPOINT,
    onBoundsChange: (Double, Double, Double, Double) -> Unit,
    onInspectChange: (Double?) -> Unit,
    modifier: Modifier = Modifier
) {
    // Estados atualizados via rememberUpdatedState para que pointerInput(Unit) jamais reinicie no meio do gesto
    val currentXMin by rememberUpdatedState(curXMin)
    val currentXMax by rememberUpdatedState(curXMax)
    val currentYMin by rememberUpdatedState(curYMin)
    val currentYMax by rememberUpdatedState(curYMax)
    val currentOnBoundsChange by rememberUpdatedState(onBoundsChange)
    val currentOnInspectChange by rememberUpdatedState(onInspectChange)
    val currentAnalysisPoints by rememberUpdatedState(config.analysisPoints)
    val currentRoots by rememberUpdatedState(detectedRoots)
    val currentExtrema by rememberUpdatedState(detectedExtrema)

    // Função de snap magnético para raízes, extremos e analysisPoints próximos ao toque
    fun snapToNotablePoint(rawMathX: Double, xSpan: Double): Double {
        val snapThreshold = xSpan * 0.025
        for (pt in currentAnalysisPoints) {
            if (abs(rawMathX - pt.x) <= snapThreshold) return pt.x
        }
        for (r in currentRoots) {
            if (abs(rawMathX - r) <= snapThreshold) return r
        }
        for (ex in currentExtrema) {
            if (abs(rawMathX - ex.x) <= snapThreshold) return ex.x
        }
        if (abs(rawMathX) <= snapThreshold * 0.6) return 0.0
        return rawMathX
    }

    val compiledPrimary = remember(activeExpression) {
        MathExpressionEvaluator.compile(activeExpression)
    }
    val compiledSecondary = remember(config.secondaryFunctionExpression) {
        config.secondaryFunctionExpression?.let { MathExpressionEvaluator.compile(it) }
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            // Gestos de Zoom por Pinça e Pan (ou Rastreamento quando isTraceDragMode está ativo)
            .pointerInput(isTraceDragMode) {
                if (isTraceDragMode) {
                    detectHorizontalDragGestures(
                        onDragStart = { offset ->
                            val w = size.width
                            if (w > 0) {
                                val span = currentXMax - currentXMin
                                val rawX = currentXMin + (offset.x / w) * span
                                currentOnInspectChange(snapToNotablePoint(rawX, span))
                            }
                        },
                        onHorizontalDrag = { change, _ ->
                            change.consume()
                            val w = size.width
                            if (w > 0) {
                                val span = currentXMax - currentXMin
                                val rawX = (currentXMin + (change.position.x / w) * span)
                                    .coerceIn(currentXMin, currentXMax)
                                currentOnInspectChange(snapToNotablePoint(rawX, span))
                            }
                        }
                    )
                } else {
                    detectTransformGestures { centroid, pan, zoom, _ ->
                        val w = size.width
                        val h = size.height
                        if (w > 0 && h > 0) {
                            val xSpan = currentXMax - currentXMin
                            val ySpan = currentYMax - currentYMin

                            val dx = (pan.x / w) * xSpan
                            val dy = (pan.y / h) * ySpan

                            val focusX = currentXMin + (centroid.x / w) * xSpan
                            val focusY = currentYMin + ((h - centroid.y) / h) * ySpan

                            val newXSpan = (xSpan / zoom).coerceIn(0.05, 500.0)
                            val newYSpan = (ySpan / zoom).coerceIn(0.05, 500.0)

                            val relX = (centroid.x / w).toDouble().coerceIn(0.0, 1.0)
                            val relY = ((h - centroid.y) / h).toDouble().coerceIn(0.0, 1.0)

                            val newXMin = focusX - relX * newXSpan - dx
                            val newXMax = newXMin + newXSpan
                            val newYMin = focusY - relY * newYSpan + dy
                            val newYMax = newYMin + newYSpan

                            currentOnBoundsChange(newXMin, newXMax, newYMin, newYMax)
                        }
                    }
                }
            }
            // Toque para Inspecionar Coordenadas com Snap Magnético
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        val w = size.width
                        if (w > 0) {
                            val span = currentXMax - currentXMin
                            val rawX = currentXMin + (offset.x / w) * span
                            currentOnInspectChange(snapToNotablePoint(rawX, span))
                        }
                    },
                    onTap = { offset ->
                        val w = size.width
                        if (w > 0) {
                            val span = currentXMax - currentXMin
                            val rawX = currentXMin + (offset.x / w) * span
                            currentOnInspectChange(snapToNotablePoint(rawX, span))
                        }
                    }
                )
            }
    ) {
        val w = size.width
        val h = size.height
        if (w <= 0f || h <= 0f) return@Canvas

        fun toPxX(x: Double): Float = (((x - curXMin) / (curXMax - curXMin)) * w).toFloat()
        fun toPxY(y: Double): Float = (h - ((y - curYMin) / (curYMax - curYMin)) * h).toFloat()

        // 1. Fundo escuro cartesiano de alto contraste
        drawRect(Color(0xFF090D16))

        clipRect(0f, 0f, w, h) {
            val xSpan = maxOf(0.05, curXMax - curXMin)
            val ySpan = maxOf(0.05, curYMax - curYMin)

            // 2. Cálculo de passo inteligente para a grade (1, 2, 5 * 10^k)
            fun getNiceStep(range: Double): Double {
                val raw = range / 7.5
                val mag = 10.0.pow(floor(log10(maxOf(1e-6, raw))))
                val norm = raw / mag
                return when {
                    norm < 1.5 -> mag
                    norm < 3.5 -> 2.0 * mag
                    norm < 7.5 -> 5.0 * mag
                    else -> 10.0 * mag
                }
            }
            val xStep = getNiceStep(xSpan)
            val yStep = getNiceStep(ySpan)
            val subStepX = xStep / 5.0
            val subStepY = yStep / 5.0

            // 3. Grade Secundária
            val subGridColor = Color(0x0CFFFFFF)
            var sgx = floor(curXMin / subStepX) * subStepX
            while (sgx <= curXMax) {
                val px = toPxX(sgx)
                drawLine(subGridColor, Offset(px, 0f), Offset(px, h), strokeWidth = 0.6f)
                sgx += subStepX
            }
            var sgy = floor(curYMin / subStepY) * subStepY
            while (sgy <= curYMax) {
                val py = toPxY(sgy)
                drawLine(subGridColor, Offset(0f, py), Offset(w, py), strokeWidth = 0.6f)
                sgy += subStepY
            }

            // 4. Grade Principal
            val mainGridColor = Color(0x22FFFFFF)
            var gx = floor(curXMin / xStep) * xStep
            while (gx <= curXMax) {
                val px = toPxX(gx)
                drawLine(mainGridColor, Offset(px, 0f), Offset(px, h), strokeWidth = 1f)
                gx += xStep
            }
            var gy = floor(curYMin / yStep) * yStep
            while (gy <= curYMax) {
                val py = toPxY(gy)
                drawLine(mainGridColor, Offset(0f, py), Offset(w, py), strokeWidth = 1f)
                gy += yStep
            }

            // 5. Eixos Cartesianos X e Y
            val axisColor = Color(0xCC94A3B8)
            val p0X = toPxX(0.0)
            val p0Y = toPxY(0.0)
            val xAxisY = p0Y.coerceIn(18f, h - 22f)
            val yAxisX = p0X.coerceIn(28f, w - 28f)

            drawLine(axisColor, Offset(0f, xAxisY), Offset(w, xAxisY), strokeWidth = 2f)
            val arrowX = Path().apply {
                moveTo(w, xAxisY)
                lineTo(w - 10f, xAxisY - 4f)
                lineTo(w - 10f, xAxisY + 4f)
                close()
            }
            drawPath(arrowX, axisColor)

            drawLine(axisColor, Offset(yAxisX, 0f), Offset(yAxisX, h), strokeWidth = 2f)
            val arrowY = Path().apply {
                moveTo(yAxisX, 0f)
                lineTo(yAxisX - 4f, 10f)
                lineTo(yAxisX + 4f, 10f)
                close()
            }
            drawPath(arrowY, axisColor)

            // 6. Rótulos numéricos dos eixos
            drawIntoCanvas { canvas ->
                val numPaint = android.graphics.Paint().apply {
                    color = 0xAA94A3B8.toInt()
                    textSize = 21f
                    typeface = android.graphics.Typeface.MONOSPACE
                    isAntiAlias = true
                }

                var lx = floor(curXMin / xStep) * xStep
                while (lx <= curXMax) {
                    if (abs(lx) > 1e-4) {
                        val px = toPxX(lx)
                        if (px in 20f..(w - 30f)) {
                            val text = formatAxisTick(lx, xStep)
                            canvas.nativeCanvas.drawText(text, px - 10f, xAxisY + 19f, numPaint)
                        }
                    }
                    lx += xStep
                }

                var ly = floor(curYMin / yStep) * yStep
                while (ly <= curYMax) {
                    if (abs(ly) > 1e-4) {
                        val py = toPxY(ly)
                        if (py in 24f..(h - 14f)) {
                            val text = formatAxisTick(ly, yStep)
                            canvas.nativeCanvas.drawText(text, yAxisX + 6f, py + 7f, numPaint)
                        }
                    }
                    ly += yStep
                }

                if (p0X in 16f..(w - 16f) && p0Y in 16f..(h - 16f)) {
                    canvas.nativeCanvas.drawText("0", p0X - 14f, p0Y + 18f, numPaint)
                }

                val axisLabelPaint = android.graphics.Paint().apply {
                    color = 0xFF67E8F9.toInt()
                    textSize = 25f
                    typeface = android.graphics.Typeface.DEFAULT_BOLD
                    isAntiAlias = true
                }
                canvas.nativeCanvas.drawText("x", w - 18f, xAxisY - 7f, axisLabelPaint)
                canvas.nativeCanvas.drawText("y", yAxisX + 10f, 22f, axisLabelPaint)
            }

            // 7. Somas de Riemann nos 4 Métodos (LEFT, RIGHT, MIDPOINT, TRAPEZOIDAL)
            if (showRiemannSum && (config.showAreaUnderCurve || config.showRiemannSum) && !isPureGeometryScene) {
                val a = config.integralA
                val b = config.integralB
                val n = riemannPartitions.coerceIn(2, 60)
                val dx = (b - a) / n
                val basePy = toPxY(0.0)

                for (i in 0 until n) {
                    val xi = a + i * dx
                    val xiNext = xi + dx
                    val rx1 = toPxX(xi)
                    val rx2 = toPxX(xiNext)

                    if (riemannType == RiemannType.TRAPEZOIDAL) {
                        val y1 = compiledPrimary.evaluate(xi)
                        val y2 = compiledPrimary.evaluate(xiNext)
                        if (!y1.isNaN() && !y2.isNaN()) {
                            val trapPath = Path().apply {
                                moveTo(rx1, basePy)
                                lineTo(rx1, toPxY(y1))
                                lineTo(rx2, toPxY(y2))
                                lineTo(rx2, basePy)
                                close()
                            }
                            drawPath(trapPath, Color(0x33F59E0B))
                            drawPath(trapPath, Color(0xAAF59E0B), style = Stroke(width = 1.2f))
                        }
                    } else {
                        val sampleX = when (riemannType) {
                            RiemannType.LEFT -> xi
                            RiemannType.RIGHT -> xiNext
                            RiemannType.MIDPOINT -> (xi + xiNext) * 0.5
                            RiemannType.TRAPEZOIDAL -> (xi + xiNext) * 0.5
                        }
                        val yVal = compiledPrimary.evaluate(sampleX)
                        if (!yVal.isNaN()) {
                            val ryTop = toPxY(maxOf(0.0, yVal))
                            val ryBottom = toPxY(minOf(0.0, yVal))
                            val rectW = abs(rx2 - rx1)
                            val rectH = abs(ryBottom - ryTop)

                            drawRect(
                                color = Color(0x33F59E0B),
                                topLeft = Offset(minOf(rx1, rx2), ryTop),
                                size = Size(rectW, rectH)
                            )
                            drawRect(
                                color = Color(0xAAF59E0B),
                                topLeft = Offset(minOf(rx1, rx2), ryTop),
                                size = Size(rectW, rectH),
                                style = Stroke(width = 1f)
                            )
                        }
                    }
                }
            }

            // 8. Área Contínua sob a Curva com Quebra Segura em Descontinuidades
            if (config.showAreaUnderCurve && !showRiemannSum && !isPureGeometryScene) {
                val a = config.integralA
                val b = config.integralB
                val areaSteps = 200
                val dArea = (b - a) / areaSteps
                val basePy = toPxY(0.0)

                var areaPath = Path()
                var segmentStarted = false
                var prevY = Double.NaN

                for (ai in 0..areaSteps) {
                    val cx = a + ai * dArea
                    val cy = compiledPrimary.evaluate(cx)
                    val isDiscontinuous = cy.isNaN() || (!prevY.isNaN() && abs(cy - prevY) > ySpan * 1.5)

                    if (isDiscontinuous) {
                        if (segmentStarted) {
                            val prevX = a + (ai - 1) * dArea
                            areaPath.lineTo(toPxX(prevX), basePy)
                            areaPath.close()
                            drawPath(areaPath, Color(0x3338BDF8))
                            areaPath = Path()
                            segmentStarted = false
                        }
                        prevY = Double.NaN
                        continue
                    }

                    val px = toPxX(cx)
                    val py = toPxY(cy).coerceIn(-h, 2f * h)
                    if (!segmentStarted) {
                        areaPath.moveTo(px, basePy)
                        areaPath.lineTo(px, py)
                        segmentStarted = true
                    } else {
                        areaPath.lineTo(px, py)
                    }
                    prevY = cy
                }

                if (segmentStarted) {
                    areaPath.lineTo(toPxX(b), basePy)
                    areaPath.close()
                    drawPath(areaPath, Color(0x3338BDF8))
                }
            }

            // 9. Primitivas Geométricas e Comandos dos Presets / Exercícios
            commandPrimitives.forEach { primitive ->
                when (primitive) {
                    is GeoGebraCommandPrimitive.Polygon2D -> {
                        val polyColor = parseSafeColor(primitive.colorHex, Color(0xFF10B981))
                        val polyPath = Path()
                        primitive.vertices.forEachIndexed { idx, (vx, vy) ->
                            val px = toPxX(vx)
                            val py = toPxY(vy)
                            if (idx == 0) polyPath.moveTo(px, py) else polyPath.lineTo(px, py)
                        }
                        polyPath.close()
                        drawPath(polyPath, polyColor.copy(alpha = 0.24f))
                        drawPath(
                            polyPath,
                            polyColor,
                            style = Stroke(width = 2.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                        )
                    }

                    is GeoGebraCommandPrimitive.Segment2D -> {
                        val segColor = parseSafeColor(primitive.colorHex, Color(0xFFEAB308))
                        drawLine(
                            color = segColor,
                            start = Offset(toPxX(primitive.x1), toPxY(primitive.y1)),
                            end = Offset(toPxX(primitive.x2), toPxY(primitive.y2)),
                            strokeWidth = 2.4.dp.toPx(),
                            pathEffect = if (primitive.isDashed) PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f) else null
                        )
                    }

                    is GeoGebraCommandPrimitive.VerticalLine -> {
                        val lineColor = parseSafeColor(primitive.colorHex, Color(0xFFEF4444))
                        val px = toPxX(primitive.x)
                        drawLine(
                            color = lineColor,
                            start = Offset(px, 0f),
                            end = Offset(px, h),
                            strokeWidth = 1.9f,
                            pathEffect = if (primitive.isDashed) PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f) else null
                        )
                    }

                    is GeoGebraCommandPrimitive.HorizontalLine -> {
                        val lineColor = parseSafeColor(primitive.colorHex, Color(0xFFF59E0B))
                        val py = toPxY(primitive.y)
                        drawLine(
                            color = lineColor,
                            start = Offset(0f, py),
                            end = Offset(w, py),
                            strokeWidth = 1.9f,
                            pathEffect = if (primitive.isDashed) PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f) else null
                        )
                    }

                    is GeoGebraCommandPrimitive.GeneralLine -> {
                        val lineColor = parseSafeColor(primitive.colorHex, Color(0xFF38BDF8))
                        if (abs(primitive.b) > 1e-7) {
                            val yAtMin = (-primitive.a * curXMin - primitive.c) / primitive.b
                            val yAtMax = (-primitive.a * curXMax - primitive.c) / primitive.b
                            drawLine(
                                color = lineColor,
                                start = Offset(toPxX(curXMin), toPxY(yAtMin)),
                                end = Offset(toPxX(curXMax), toPxY(yAtMax)),
                                strokeWidth = 2.2f,
                                pathEffect = if (primitive.isDashed) PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f) else null
                            )
                        } else if (abs(primitive.a) > 1e-7) {
                            val xConst = -primitive.c / primitive.a
                            val px = toPxX(xConst)
                            drawLine(
                                color = lineColor,
                                start = Offset(px, 0f),
                                end = Offset(px, h),
                                strokeWidth = 2.2f,
                                pathEffect = if (primitive.isDashed) PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f) else null
                            )
                        }
                    }

                    is GeoGebraCommandPrimitive.Circle2D -> {
                        val cColor = parseSafeColor(primitive.colorHex, Color(0xFF38BDF8))
                        val circlePath = Path()
                        val steps = 180
                        for (i in 0..steps) {
                            val theta = (2.0 * PI * i) / steps
                            val cx = primitive.centerX + primitive.radius * cos(theta)
                            val cy = primitive.centerY + primitive.radius * sin(theta)
                            val px = toPxX(cx)
                            val py = toPxY(cy)
                            if (i == 0) circlePath.moveTo(px, py) else circlePath.lineTo(px, py)
                        }
                        circlePath.close()
                        drawPath(
                            path = circlePath,
                            color = cColor,
                            style = Stroke(
                                width = 2.4.dp.toPx(),
                                pathEffect = if (primitive.isDashed) PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f) else null
                            )
                        )
                    }

                    is GeoGebraCommandPrimitive.Ellipse2D -> {
                        val eColor = parseSafeColor(primitive.colorHex, Color(0xFF3B82F6))
                        val ellipsePath = Path()
                        val steps = 180
                        for (i in 0..steps) {
                            val theta = (2.0 * PI * i) / steps
                            val cx = primitive.centerX + primitive.rx * cos(theta)
                            val cy = primitive.centerY + primitive.ry * sin(theta)
                            val px = toPxX(cx)
                            val py = toPxY(cy)
                            if (i == 0) ellipsePath.moveTo(px, py) else ellipsePath.lineTo(px, py)
                        }
                        ellipsePath.close()
                        drawPath(
                            path = ellipsePath,
                            color = eColor.copy(alpha = 0.14f)
                        )
                        drawPath(
                            path = ellipsePath,
                            color = eColor,
                            style = Stroke(width = 2.6.dp.toPx())
                        )
                    }

                    is GeoGebraCommandPrimitive.ExtraFunction -> {
                        val fColor = parseSafeColor(primitive.colorHex, Color(0xFFC084FC))
                        val compiledExtra = MathExpressionEvaluator.compile(primitive.expression)
                        drawSmoothCompiledCurve(
                            compiled = compiledExtra,
                            curXMin = curXMin,
                            curXMax = curXMax,
                            curYMin = curYMin,
                            curYMax = curYMax,
                            w = w,
                            h = h,
                            curveColor = fColor,
                            strokeWidth = 2.2f,
                            isDashed = primitive.isDashed
                        )
                    }

                    is GeoGebraCommandPrimitive.NamedPoint -> Unit
                }
            }

            // 9.b Assíntotas Verticais Detectadas Automaticamente (quando showAsymptotesOverlay está ativo)
            detectedAsymptotes.forEach { poleX ->
                val px = toPxX(poleX)
                drawLine(
                    color = Color(0xCCEF4444),
                    start = Offset(px, 0f),
                    end = Offset(px, h),
                    strokeWidth = 1.8f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)
                )
            }

            // 10. Curva Secundária g(x) explícita da config
            if (compiledSecondary != null) {
                drawSmoothCompiledCurve(
                    compiled = compiledSecondary,
                    curXMin = curXMin,
                    curXMax = curXMax,
                    curYMin = curYMin,
                    curYMax = curYMax,
                    w = w,
                    h = h,
                    curveColor = Color(0xFFC084FC),
                    strokeWidth = 2.4f
                )
            }

            // 11. Curva Principal f(x) com AST Compilada e Amostragem Adaptativa
            if (!isPureGeometryScene) {
                drawSmoothCompiledCurve(
                    compiled = compiledPrimary,
                    curXMin = curXMin,
                    curXMax = curXMax,
                    curYMin = curYMin,
                    curYMax = curYMax,
                    w = w,
                    h = h,
                    curveColor = Color(0xFF38BDF8),
                    strokeWidth = 3.2f
                )
            }

            // 12. Reta Tangente Interativa
            if (config.showTangentSlider && !isPureGeometryScene) {
                val y0 = compiledPrimary.evaluate(tangentX)
                if (!y0.isNaN()) {
                    val slope = MathExpressionEvaluator.derivative(activeExpression, tangentX)
                    if (!slope.isNaN()) {
                        val yA = y0 + slope * (curXMin - tangentX)
                        val yB = y0 + slope * (curXMax - tangentX)
                        drawLine(
                            color = Color(0xFFFACC15),
                            start = Offset(toPxX(curXMin), toPxY(yA)),
                            end = Offset(toPxX(curXMax), toPxY(yB)),
                            strokeWidth = 2.2f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)
                        )
                        drawCircle(
                            color = Color(0xFFFACC15),
                            radius = 6.dp.toPx(),
                            center = Offset(toPxX(tangentX), toPxY(y0))
                        )
                    }
                }
            }

            // 13. Linha Guia de Inspeção (Cursor ao Tocar / Rastrear)
            if (inspectedX != null && !isPureGeometryScene) {
                val ix = inspectedX
                if (ix in curXMin..curXMax) {
                    val ipx = toPxX(ix)
                    val iy = compiledPrimary.evaluate(ix)
                    drawLine(
                        color = Color(0x7738BDF8),
                        start = Offset(ipx, 0f),
                        end = Offset(ipx, h),
                        strokeWidth = 1.2f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f)
                    )
                    if (!iy.isNaN()) {
                        val ipy = toPxY(iy)
                        drawLine(
                            color = Color(0x4438BDF8),
                            start = Offset(0f, ipy),
                            end = Offset(w, ipy),
                            strokeWidth = 1f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(4f, 6f), 0f)
                        )
                        drawCircle(
                            color = Color(0xFF38BDF8),
                            radius = 5.5.dp.toPx(),
                            center = Offset(ipx, ipy)
                        )
                        drawCircle(
                            color = Color(0xFF090D16),
                            radius = 2.5.dp.toPx(),
                            center = Offset(ipx, ipy)
                        )
                    }
                }
            }

            // 14. Pontos Notáveis (analysisPoints + Raízes + Extremos + Inflexão) com Rótulos Anti-Colisão
            val occupiedLabelRects = mutableListOf<Rect>()

            // Combina os pontos configurados com os detectados dinamicamente
            val allPointsToRender = buildList {
                addAll(config.analysisPoints)
                detectedRoots.forEach { rx ->
                    if (none { abs(it.x - rx) < 1e-2 && abs(it.y) < 1e-2 }) {
                        add(GeoGebraPoint("R", rx, 0.0, "Raiz", isOpenCircle = false, colorHex = "#10B981"))
                    }
                }
                detectedExtrema.forEach { ex ->
                    if (none { abs(it.x - ex.x) < 1e-2 && abs(it.y - ex.y) < 1e-2 }) {
                        val hex = if (ex.type == CriticalPointType.LOCAL_MAXIMUM) "#34D399" else "#F59E0B"
                        add(GeoGebraPoint("E", ex.x, ex.y, ex.label, isOpenCircle = false, colorHex = hex))
                    }
                }
                detectedInflections.forEach { inf ->
                    if (none { abs(it.x - inf.x) < 1e-2 && abs(it.y - inf.y) < 1e-2 }) {
                        add(GeoGebraPoint("I", inf.x, inf.y, inf.label, isOpenCircle = false, colorHex = "#C084FC"))
                    }
                }
            }

            allPointsToRender.forEach { pt ->
                if (pt.x in curXMin..curXMax && pt.y in curYMin..curYMax) {
                    val pX = toPxX(pt.x)
                    val pY = toPxY(pt.y)
                    val ptColor = parseSafeColor(pt.colorHex, Color(0xFFEF4444))

                    if (pt.isOpenCircle) {
                        drawCircle(
                            color = Color(0xFF090D16),
                            radius = 6.dp.toPx(),
                            center = Offset(pX, pY)
                        )
                        drawCircle(
                            color = ptColor,
                            radius = 6.dp.toPx(),
                            center = Offset(pX, pY),
                            style = Stroke(width = 2.5.dp.toPx())
                        )
                    } else {
                        drawCircle(
                            color = Color(0xFF090D16),
                            radius = 6.dp.toPx(),
                            center = Offset(pX, pY)
                        )
                        drawCircle(
                            color = ptColor,
                            radius = 4.8.dp.toPx(),
                            center = Offset(pX, pY)
                        )
                    }

                    // Posicionamento inteligente anti-colisão do rótulo
                    drawIntoCanvas { canvas ->
                        val ptPaint = android.graphics.Paint().apply {
                            color = 0xFFF8FAFC.toInt()
                            textSize = 20f
                            typeface = android.graphics.Typeface.DEFAULT_BOLD
                            isAntiAlias = true
                        }
                        val label = "${pt.label} (${formatCoord(pt.x)}, ${formatCoord(pt.y)})"
                        val textWidth = ptPaint.measureText(label)
                        val textHeight = 22f

                        val candidates = listOf(
                            Offset(pX + 10f, pY - 10f),
                            Offset(pX + 10f, pY + 22f),
                            Offset(pX - textWidth - 10f, pY - 10f),
                            Offset(pX - textWidth - 10f, pY + 22f),
                            Offset(pX - textWidth * 0.5f, pY - 18f)
                        )

                        val chosenOffset = candidates.firstOrNull { cand ->
                            val rect = Rect(
                                left = cand.x - 2f,
                                top = cand.y - textHeight,
                                right = cand.x + textWidth + 2f,
                                bottom = cand.y + 4f
                            )
                            val insideBounds = rect.left >= 4f && rect.right <= w - 4f && rect.top >= 4f && rect.bottom <= h - 4f
                            val noOverlap = occupiedLabelRects.none { it.overlaps(rect) }
                            insideBounds && noOverlap
                        } ?: candidates.first().let { fallback ->
                            Offset(
                                x = fallback.x.coerceIn(6f, maxOf(6f, w - textWidth - 6f)),
                                y = fallback.y.coerceIn(textHeight + 4f, maxOf(textHeight + 4f, h - 8f))
                            )
                        }

                        occupiedLabelRects.add(
                            Rect(
                                left = chosenOffset.x - 2f,
                                top = chosenOffset.y - textHeight,
                                right = chosenOffset.x + textWidth + 2f,
                                bottom = chosenOffset.y + 4f
                            )
                        )

                        canvas.nativeCanvas.drawText(label, chosenOffset.x, chosenOffset.y, ptPaint)
                    }
                }
            }
        }
    }
}

/**
 * Desenha curvas analíticas suaves usando diretamente a AST compilada (`CompiledMathExpression`).
 * Possui amostragem adaptativa de refinamento por sub-passos em regiões de alta inclinação e
 * interrupção estrita em assíntotas verticais e saltos descontínuos.
 */
private fun DrawScope.drawSmoothCompiledCurve(
    compiled: CompiledMathExpression,
    curXMin: Double,
    curXMax: Double,
    curYMin: Double,
    curYMax: Double,
    w: Float,
    h: Float,
    curveColor: Color,
    strokeWidth: Float,
    isDashed: Boolean = false
) {
    if (!compiled.isValid) return

    fun toPxX(x: Double): Float = (((x - curXMin) / (curXMax - curXMin)) * w).toFloat()
    fun toPxY(y: Double): Float = (h - ((y - curYMin) / (curYMax - curYMin)) * h).toFloat()

    val ySpan = maxOf(0.05, curYMax - curYMin)
    // Número de passos adaptativo baseado na largura física do Canvas
    val baseSteps = (w.toInt().coerceIn(480, 960))
    val dx = (curXMax - curXMin) / baseSteps

    val strokeStyle = Stroke(
        width = strokeWidth.dp.toPx(),
        cap = StrokeCap.Round,
        join = StrokeJoin.Round,
        pathEffect = if (isDashed) PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f) else null
    )

    var path = Path()
    var isDrawing = false
    var prevX = Double.NaN
    var prevY = Double.NaN

    // Limite seguro relativo à janela vertical atual (evita cortar polinômios legítimos em zoom-out)
    val maxAllowedAbsY = maxOf(abs(curYMin), abs(curYMax)) + ySpan * 15.0

    for (si in 0..baseSteps) {
        val cx = curXMin + si * dx
        val cy = compiled.evaluate(cx)

        if (cy.isNaN() || cy.isInfinite() || abs(cy) > maxAllowedAbsY) {
            if (isDrawing) {
                drawPath(path = path, color = curveColor, style = strokeStyle)
                path = Path()
                isDrawing = false
            }
            prevY = Double.NaN
            prevX = cx
            continue
        }

        // Verifica se há salto abrupto ou assíntota vertical entre prevX e cx
        if (!prevY.isNaN()) {
            val dy = abs(cy - prevY)
            if (dy > ySpan * 0.25) {
                // Amostragem adaptativa no ponto médio para distinguir curva íngreme real de descontinuidade/pólo
                val midX = (prevX + cx) * 0.5
                val midY = compiled.evaluate(midX)
                val isMonotonicContinuous = !midY.isNaN() &&
                    ((midY in prevY..cy) || (midY in cy..prevY)) &&
                    dy < ySpan * 2.5

                if (!isMonotonicContinuous) {
                    if (isDrawing) {
                        drawPath(path = path, color = curveColor, style = strokeStyle)
                        path = Path()
                        isDrawing = false
                    }
                    prevX = cx
                    prevY = cy
                    continue
                } else {
                    // Adiciona o ponto intermediário refinado para suavizar alta curvatura
                    path.lineTo(toPxX(midX), toPxY(midY))
                }
            }
        }

        val px = toPxX(cx)
        val py = toPxY(cy)

        if (!isDrawing) {
            path.moveTo(px, py)
            isDrawing = true
        } else {
            path.lineTo(px, py)
        }
        prevX = cx
        prevY = cy
    }

    if (isDrawing) {
        drawPath(path = path, color = curveColor, style = strokeStyle)
    }
}

private fun parseSafeColor(hex: String, fallback: Color): Color {
    return try {
        Color(android.graphics.Color.parseColor(hex))
    } catch (_: Throwable) {
        fallback
    }
}

private fun formatAxisTick(value: Double, step: Double): String {
    return when {
        abs(value - round(value)) < 1e-4 && step >= 1.0 -> value.roundToInt().toString()
        step >= 0.1 -> String.format(Locale.US, "%.1f", value)
        else -> String.format(Locale.US, "%.2f", value)
    }
}

private fun formatCoord(value: Double): String {
    return if (abs(value - round(value)) < 1e-3) {
        value.roundToInt().toString()
    } else {
        String.format(Locale.US, "%.2f", value)
    }
}
