package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Timeline
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.math.GeoGebraGraphConfig
import com.example.data.math.GeoGebraPoint
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.util.Locale
import kotlin.math.*

/**
 * Visualizador Matemático Interativo Nativo do Knowverse.
 * Executa 100% offline em Jetpack Compose Canvas, proporcionando visualização cartesiana de alta precisão,
 * zoom por pinça, navegação por arrasto, inspeção tátil em tempo real de coordenadas, cálculo rigoroso
 * de derivadas, assíntotas, áreas de integração e somas de Riemann.
 */
@Composable
fun GeoGebraMathGraphView(
    config: GeoGebraGraphConfig,
    modifier: Modifier = Modifier,
    isFullScreenMode: Boolean = false,
    onToggleFullScreen: (() -> Unit)? = null
) {
    var activeExpression by remember(config.title, config.functionExpression) {
        mutableStateOf(config.functionExpression)
    }
    var tangentX by remember(config.title, config.functionExpression) {
        mutableDoubleStateOf(1.0.coerceIn(config.xMin + 0.5, config.xMax - 0.5))
    }
    var isCustomizing by remember { mutableStateOf(false) }
    var customInputText by remember { mutableStateOf(config.functionExpression) }
    var isFullScreenDialogVisible by remember { mutableStateOf(false) }
    var riemannPartitions by remember(config.title) { mutableIntStateOf(config.riemannPartitions) }
    var showRiemann by remember(config.title) { mutableStateOf(config.showRiemannSum) }

    // Estado da visualização cartesiana
    var curXMin by remember(config.title, activeExpression) { mutableDoubleStateOf(config.xMin) }
    var curXMax by remember(config.title, activeExpression) { mutableDoubleStateOf(config.xMax) }
    var curYMin by remember(config.title, activeExpression) { mutableDoubleStateOf(config.yMin) }
    var curYMax by remember(config.title, activeExpression) { mutableDoubleStateOf(config.yMax) }

    // Inspeção de coordenadas (Hover / Tap tracking)
    var inspectedX by remember { mutableStateOf<Double?>(null) }

    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header do Gráfico com identificador matemático e título
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
                            imageVector = Icons.Default.ShowChart,
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
                        Text(
                            text = "f(x) = $activeExpression",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFF67E8F9),
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Botão Personalizar Função
                    IconButton(
                        onClick = {
                            isCustomizing = !isCustomizing
                            if (isCustomizing) customInputText = activeExpression
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Personalizar Função",
                            tint = if (isCustomizing) GoldXp else TextSecondary,
                            modifier = Modifier.size(17.dp)
                        )
                    }

                    // Botão Redefinir Vista
                    IconButton(
                        onClick = {
                            activeExpression = config.functionExpression
                            tangentX = 1.0.coerceIn(config.xMin + 0.5, config.xMax - 0.5)
                            curXMin = config.xMin
                            curXMax = config.xMax
                            curYMin = config.yMin
                            curYMax = config.yMax
                            inspectedX = null
                        },
                        modifier = Modifier.size(32.dp)
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
                            modifier = Modifier.size(32.dp)
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

            // Painel de entrada para digitar função customizada
            AnimatedVisibility(visible = isCustomizing) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(SurfaceElevated, RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Digite qualquer expressão matemática para plotar em tempo real:",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GeoGebraCustomInputField(
                            valText = customInputText,
                            onValChange = { customInputText = it },
                            placeholder = "ex: (x^2 - 4)/(x - 2) ou sin(x)/x",
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                if (customInputText.isNotBlank()) {
                                    activeExpression = customInputText.trim()
                                    isCustomizing = false
                                    inspectedX = null
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.height(42.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = "Plotar", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Plotar", fontSize = 12.sp)
                        }
                    }
                }
            }

            // Presets de Funções Relacionadas
            if (config.presetFunctions.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    config.presetFunctions.forEach { preset ->
                        val isSelected = activeExpression == preset
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                activeExpression = preset
                                inspectedX = null
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
                }
            }

            // Controles de Derivada / Reta Tangente
            if (config.showTangentSlider) {
                Surface(
                    color = SurfaceElevated,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                        val slope = MathExpressionEvaluator.derivative(activeExpression, tangentX)
                        val y0 = MathExpressionEvaluator.evaluate(activeExpression, tangentX)
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
                                    text = "x₀ = ${String.format(Locale.US, "%.2f", tangentX)}  |  f(x₀) = ${if (y0.isNaN()) "Indef." else String.format(Locale.US, "%.2f", y0)}",
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
                        Slider(
                            value = tangentX.toFloat(),
                            onValueChange = { newVal ->
                                tangentX = newVal.toDouble()
                            },
                            valueRange = (curXMin + 0.1).toFloat()..(curXMax - 0.1).toFloat(),
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

            // Controles de Integração e Somas de Riemann (se aplicável)
            if (config.showAreaUnderCurve || config.showRiemannSum) {
                val integralVal = MathExpressionEvaluator.definiteIntegral(
                    activeExpression,
                    config.integralA,
                    config.integralB
                )
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
                                    text = "∫ [${config.integralA}, ${config.integralB}] f(x)dx = ${if (integralVal.isNaN()) "Indef." else String.format(Locale.US, "%.4f", integralVal)}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFF38BDF8),
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Monospace
                                    )
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Riemann (n=${riemannPartitions})",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = if (showRiemann) GoldXp else TextSecondary,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier
                                        .clickable { showRiemann = !showRiemann }
                                        .padding(4.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Container do Visualizador Matemático Nativo
            val graphHeight = if (isFullScreenMode) 480.dp else 260.dp
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(graphHeight)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF090D16))
                    .border(1.dp, BorderSubtle.copy(alpha = 0.8f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                NativeMathGraphCanvas(
                    config = config,
                    activeExpression = activeExpression,
                    tangentX = tangentX,
                    curXMin = curXMin,
                    curXMax = curXMax,
                    curYMin = curYMin,
                    curYMax = curYMax,
                    inspectedX = inspectedX,
                    showRiemannSum = showRiemann,
                    riemannPartitions = riemannPartitions,
                    onBoundsChange = { nXMin, nXMax, nYMin, nYMax ->
                        curXMin = nXMin
                        curXMax = nXMax
                        curYMin = nYMin
                        curYMax = nYMax
                    },
                    onInspectChange = { inspectedX = it },
                    modifier = Modifier.fillMaxSize()
                )

                // Tooltip de inspeção flutuante ao tocar ou arrastar na curva
                if (inspectedX != null) {
                    val ix = inspectedX!!
                    val iy = MathExpressionEvaluator.evaluate(activeExpression, ix)
                    Surface(
                        color = Color(0xEE0F172A),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF38BDF8)),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
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
                                text = "x = ${String.format(Locale.US, "%.3f", ix)}  |  y = ${if (iy.isNaN()) "Indefinido" else String.format(Locale.US, "%.3f", iy)}",
                                color = Color(0xFFF8FAFC),
                                fontFamily = FontFamily.Monospace,
                                fontSize = 11.sp,
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
                            .clickable {
                                val xSpan = curXMax - curXMin
                                val ySpan = curYMax - curYMin
                                curXMin += xSpan * 0.15
                                curXMax -= xSpan * 0.15
                                curYMin += ySpan * 0.15
                                curYMax -= ySpan * 0.15
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
                            .clickable {
                                val xSpan = curXMax - curXMin
                                val ySpan = curYMax - curYMin
                                curXMin -= xSpan * 0.2
                                curXMax += xSpan * 0.2
                                curYMin -= ySpan * 0.2
                                curYMax += ySpan * 0.2
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
                            .clickable {
                                curXMin = config.xMin
                                curXMax = config.xMax
                                curYMin = config.yMin
                                curYMax = config.yMax
                                inspectedX = null
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.RestartAlt, contentDescription = "Reset", tint = Color(0xFF94A3B8), modifier = Modifier.size(14.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Legenda e Análise Geométrica dos Pontos
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

                    if (config.analysisPoints.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            config.analysisPoints.forEach { pt ->
                                Surface(
                                    color = ObsidianSurface,
                                    shape = RoundedCornerShape(6.dp),
                                    border = androidx.compose.foundation.BorderStroke(0.8.dp, BorderSubtle)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .clip(CircleShape)
                                                .background(
                                                    if (pt.isOpenCircle) Color.Transparent else Color(0xFFEF4444)
                                                )
                                                .border(
                                                    1.5.dp,
                                                    Color(0xFFEF4444),
                                                    CircleShape
                                                )
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

    // Diálogo Modal de Tela Cheia
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
                                imageVector = Icons.Default.ShowChart,
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
                            onToggleFullScreen = { isFullScreenDialogVisible = false }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GeoGebraCustomInputField(
    valText: String,
    onValChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valText,
        onValueChange = onValChange,
        placeholder = { Text(placeholder, fontSize = 12.sp, color = TextSecondary) },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryViolet,
            unfocusedBorderColor = BorderSubtle,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.height(50.dp)
    )
}

/**
 * Canvas Nativo de Desenho Matemático com Escala Adaptativa, Grade Inteligente,
 * Interrupção de Descontinuidades e Gestos Fluidos de Pinch-to-Zoom e Pan.
 */
@Composable
fun NativeMathGraphCanvas(
    config: GeoGebraGraphConfig,
    activeExpression: String,
    tangentX: Double,
    curXMin: Double,
    curXMax: Double,
    curYMin: Double,
    curYMax: Double,
    inspectedX: Double?,
    showRiemannSum: Boolean = false,
    riemannPartitions: Int = 8,
    onBoundsChange: (Double, Double, Double, Double) -> Unit,
    onInspectChange: (Double?) -> Unit,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
            // Gestos: Zoom por Pinça (pinch) e Pan simultâneos
            .pointerInput(curXMin, curXMax, curYMin, curYMax) {
                detectTransformGestures { centroid, pan, zoom, _ ->
                    val w = size.width
                    val h = size.height
                    if (w > 0 && h > 0) {
                        val xSpan = curXMax - curXMin
                        val ySpan = curYMax - curYMin

                        // 1. Pan (translação)
                        val dx = (pan.x / w) * xSpan
                        val dy = (pan.y / h) * ySpan

                        // 2. Zoom focalizado no centróide
                        val focusX = curXMin + (centroid.x / w) * xSpan
                        val focusY = curYMin + ((h - centroid.y) / h) * ySpan

                        val newXSpan = (xSpan / zoom).coerceIn(0.05, 500.0)
                        val newYSpan = (ySpan / zoom).coerceIn(0.05, 500.0)

                        val relX = (centroid.x / w).toDouble().coerceIn(0.0, 1.0)
                        val relY = ((h - centroid.y) / h).toDouble().coerceIn(0.0, 1.0)

                        val newXMin = focusX - relX * newXSpan - dx
                        val newXMax = newXMin + newXSpan
                        val newYMin = focusY - relY * newYSpan + dy
                        val newYMax = newYMin + newYSpan

                        onBoundsChange(newXMin, newXMax, newYMin, newYMax)
                    }
                }
            }
            // Toque para Inspecionar Coordenadas
            .pointerInput(curXMin, curXMax) {
                detectTapGestures(
                    onPress = { offset ->
                        val w = size.width
                        if (w > 0) {
                            val mathX = curXMin + (offset.x / w) * (curXMax - curXMin)
                            onInspectChange(mathX)
                        }
                    },
                    onTap = { offset ->
                        val w = size.width
                        if (w > 0) {
                            val mathX = curXMin + (offset.x / w) * (curXMax - curXMin)
                            onInspectChange(mathX)
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

        // 3. Grade Secundária (sutil)
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

        // 5. Eixos Cartesiano X e Y
        val axisColor = Color(0xCC94A3B8)
        val p0X = toPxX(0.0)
        val p0Y = toPxY(0.0)
        val xAxisY = p0Y.coerceIn(16f, h - 16f)
        val yAxisX = p0X.coerceIn(24f, w - 24f)

        // Eixo X com Seta
        drawLine(axisColor, Offset(0f, xAxisY), Offset(w, xAxisY), strokeWidth = 2f)
        val arrowX = Path().apply {
            moveTo(w, xAxisY)
            lineTo(w - 10f, xAxisY - 4f)
            lineTo(w - 10f, xAxisY + 4f)
            close()
        }
        drawPath(arrowX, axisColor)

        // Eixo Y com Seta
        drawLine(axisColor, Offset(yAxisX, 0f), Offset(yAxisX, h), strokeWidth = 2f)
        val arrowY = Path().apply {
            moveTo(yAxisX, 0f)
            lineTo(yAxisX - 4f, 10f)
            lineTo(yAxisX + 4f, 10f)
            close()
        }
        drawPath(arrowY, axisColor)

        // 6. Rótulos numéricos dos eixos com prevenção de sobreposição
        drawIntoCanvas { canvas ->
            val numPaint = android.graphics.Paint().apply {
                color = 0xAA94A3B8.toInt()
                textSize = 22f
                typeface = android.graphics.Typeface.MONOSPACE
                isAntiAlias = true
            }

            // X Labels
            var lx = floor(curXMin / xStep) * xStep
            while (lx <= curXMax) {
                if (abs(lx) > 1e-4) {
                    val px = toPxX(lx)
                    val text = if (abs(lx - round(lx)) < 1e-4) lx.toInt().toString() else String.format(Locale.US, "%.1f", lx)
                    canvas.nativeCanvas.drawText(text, px - 10f, xAxisY + 20f, numPaint)
                }
                lx += xStep
            }

            // Y Labels
            var ly = floor(curYMin / yStep) * yStep
            while (ly <= curYMax) {
                if (abs(ly) > 1e-4) {
                    val py = toPxY(ly)
                    val text = if (abs(ly - round(ly)) < 1e-4) ly.toInt().toString() else String.format(Locale.US, "%.1f", ly)
                    canvas.nativeCanvas.drawText(text, yAxisX + 6f, py + 8f, numPaint)
                }
                ly += yStep
            }

            // Rótulo da Origem '0'
            if (p0X in 0f..w && p0Y in 0f..h) {
                canvas.nativeCanvas.drawText("0", p0X - 14f, p0Y + 18f, numPaint)
            }

            // Nomes dos eixos 'x' e 'y'
            val axisLabelPaint = android.graphics.Paint().apply {
                color = 0xFF67E8F9.toInt()
                textSize = 26f
                typeface = android.graphics.Typeface.DEFAULT_BOLD
                isAntiAlias = true
            }
            canvas.nativeCanvas.drawText("x", w - 20f, xAxisY - 6f, axisLabelPaint)
            canvas.nativeCanvas.drawText("y", yAxisX + 10f, 24f, axisLabelPaint)
        }

        // 7. Somas de Riemann (se ativadas, ex: aproximação da integral)
        if (showRiemannSum && (config.showAreaUnderCurve || config.showRiemannSum)) {
            val a = config.integralA
            val b = config.integralB
            val n = riemannPartitions.coerceIn(2, 60)
            val dx = (b - a) / n

            for (i in 0 until n) {
                val xi = a + i * dx
                val midX = xi + dx / 2.0
                val yVal = MathExpressionEvaluator.evaluate(activeExpression, midX)
                if (!yVal.isNaN() && !yVal.isInfinite()) {
                    val rx1 = toPxX(xi)
                    val rx2 = toPxX(xi + dx)
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

        // 8. Área Contínua sob a Curva (Integral Definida)
        if (config.showAreaUnderCurve && !showRiemannSum) {
            val areaPath = Path()
            val a = config.integralA
            val b = config.integralB
            val areaSteps = 160
            val dArea = (b - a) / areaSteps
            val basePy = toPxY(0.0)

            areaPath.moveTo(toPxX(a), basePy)
            var hasPoints = false

            for (ai in 0..areaSteps) {
                val cx = a + ai * dArea
                val cy = MathExpressionEvaluator.evaluate(activeExpression, cx)
                if (!cy.isNaN() && !cy.isInfinite()) {
                    areaPath.lineTo(toPxX(cx), toPxY(cy))
                    hasPoints = true
                }
            }
            if (hasPoints) {
                areaPath.lineTo(toPxX(b), basePy)
                areaPath.close()
                drawPath(areaPath, Color(0x3338BDF8))
            }
        }

        // 9. Assíntotas Verticais e Horizontais (do config.commands e das assíntotas declaradas)
        config.commands.forEach { cmd ->
            val trimmed = cmd.trim()
            if (trimmed.startsWith("x =") || trimmed.startsWith("x=")) {
                val cVal = trimmed.replace("x", "").replace("=", "").trim().toDoubleOrNull()
                if (cVal != null) {
                    val px = toPxX(cVal)
                    drawLine(
                        color = Color(0xFFEF4444),
                        start = Offset(px, 0f),
                        end = Offset(px, h),
                        strokeWidth = 1.8f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)
                    )
                }
            } else if (trimmed.startsWith("y =") || trimmed.startsWith("y=")) {
                val cVal = trimmed.replace("y", "").replace("=", "").trim().toDoubleOrNull()
                if (cVal != null) {
                    val py = toPxY(cVal)
                    drawLine(
                        color = Color(0xFFF59E0B),
                        start = Offset(0f, py),
                        end = Offset(w, py),
                        strokeWidth = 1.8f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)
                    )
                }
            }
        }

        // 10. Curva Secundária g(x) (se houver, para comparação de funções)
        if (config.secondaryFunctionExpression != null) {
            val gExpr = config.secondaryFunctionExpression
            drawSmoothMathCurve(
                expression = gExpr,
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

        // 11. Curva Principal f(x) com Amostragem Adaptativa e Descontinuidade Segura
        drawSmoothMathCurve(
            expression = activeExpression,
            curXMin = curXMin,
            curXMax = curXMax,
            curYMin = curYMin,
            curYMax = curYMax,
            w = w,
            h = h,
            curveColor = Color(0xFF38BDF8),
            strokeWidth = 3.2f
        )

        // 12. Reta Tangente Interativa
        if (config.showTangentSlider) {
            val y0 = MathExpressionEvaluator.evaluate(activeExpression, tangentX)
            if (!y0.isNaN() && !y0.isInfinite()) {
                val slope = MathExpressionEvaluator.derivative(activeExpression, tangentX)
                if (!slope.isNaN() && !slope.isInfinite()) {
                    val yA = y0 + slope * (curXMin - tangentX)
                    val yB = y0 + slope * (curXMax - tangentX)
                    drawLine(
                        color = Color(0xFFFACC15),
                        start = Offset(toPxX(curXMin), toPxY(yA)),
                        end = Offset(toPxX(curXMax), toPxY(yB)),
                        strokeWidth = 2.2f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)
                    )
                    // Ponto de Tangência T0 (amarelo)
                    drawCircle(
                        color = Color(0xFFFACC15),
                        radius = 6.dp.toPx(),
                        center = Offset(toPxX(tangentX), toPxY(y0))
                    )
                }
            }
        }

        // 13. Linha Guia de Inspeção (Cursor ao Tocar)
        if (inspectedX != null) {
            val ix = inspectedX!!
            if (ix in curXMin..curXMax) {
                val ipx = toPxX(ix)
                val iy = MathExpressionEvaluator.evaluate(activeExpression, ix)
                drawLine(
                    color = Color(0x6638BDF8),
                    start = Offset(ipx, 0f),
                    end = Offset(ipx, h),
                    strokeWidth = 1f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f)
                )
                if (!iy.isNaN() && !iy.isInfinite()) {
                    val ipy = toPxY(iy)
                    drawCircle(
                        color = Color(0xFF38BDF8),
                        radius = 5.dp.toPx(),
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

        // 14. Pontos Notáveis de Análise (raízes, extremos e buracos de descontinuidade)
        config.analysisPoints.forEach { pt ->
            if (pt.x in curXMin..curXMax && pt.y in curYMin..curYMax) {
                val pX = toPxX(pt.x)
                val pY = toPxY(pt.y)
                val ptColor = try {
                    Color(android.graphics.Color.parseColor(pt.colorHex))
                } catch (_: Throwable) {
                    Color(0xFFEF4444)
                }

                if (pt.isOpenCircle) {
                    // Buraco aberto de descontinuidade removível
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
                        color = ptColor,
                        radius = 5.dp.toPx(),
                        center = Offset(pX, pY)
                    )
                }

                drawIntoCanvas { canvas ->
                    val ptPaint = android.graphics.Paint().apply {
                        color = 0xFFF8FAFC.toInt()
                        textSize = 22f
                        typeface = android.graphics.Typeface.DEFAULT_BOLD
                        isAntiAlias = true
                    }
                    val label = "${pt.label} (${String.format(Locale.US, "%.1f", pt.x)}, ${String.format(Locale.US, "%.1f", pt.y)})"
                    canvas.nativeCanvas.drawText(label, pX + 10f, pY - 8f, ptPaint)
                }
            }
        }
    }
}

/**
 * Desenha curvas analíticas suaves com detecção precisa de assíntotas e descontinuidades.
 * Nunca conecta pontos que cruzam assíntotas verticais ou saltos descontinuos.
 */
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawSmoothMathCurve(
    expression: String,
    curXMin: Double,
    curXMax: Double,
    curYMin: Double,
    curYMax: Double,
    w: Float,
    h: Float,
    curveColor: Color,
    strokeWidth: Float
) {
    fun toPxX(x: Double): Float = (((x - curXMin) / (curXMax - curXMin)) * w).toFloat()
    fun toPxY(y: Double): Float = (h - ((y - curYMin) / (curYMax - curYMin)) * h).toFloat()

    val ySpan = maxOf(0.1, curYMax - curYMin)
    val steps = 500
    val dx = (curXMax - curXMin) / steps

    var path = Path()
    var isDrawing = false
    var prevY = Double.NaN
    var prevSlope = Double.NaN

    for (si in 0..steps) {
        val cx = curXMin + si * dx
        val cy = MathExpressionEvaluator.evaluate(expression, cx)

        // 1. Fora do domínio matemático (NaN ou infinito)
        if (cy.isNaN() || cy.isInfinite() || abs(cy) > 5000.0) {
            if (isDrawing) {
                drawPath(
                    path = path,
                    color = curveColor,
                    style = Stroke(width = strokeWidth.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                )
                path = Path()
                isDrawing = false
            }
            prevY = Double.NaN
            continue
        }

        // 2. Detecção de Descontinuidade Vertical / Assíntota
        val isVerticalAsymptoteJump = !prevY.isNaN() && (
            (prevY * cy < 0 && abs(cy - prevY) > ySpan * 0.9) ||
            abs(cy - prevY) > ySpan * 1.8
        )

        if (isVerticalAsymptoteJump) {
            if (isDrawing) {
                drawPath(
                    path = path,
                    color = curveColor,
                    style = Stroke(width = strokeWidth.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                )
                path = Path()
                isDrawing = false
            }
            prevY = cy
            continue
        }

        val px = toPxX(cx)
        val py = toPxY(cy)

        if (!isDrawing) {
            path.moveTo(px, py)
            isDrawing = true
        } else {
            path.lineTo(px, py)
        }
        prevY = cy
    }

    if (isDrawing) {
        drawPath(
            path = path,
            color = curveColor,
            style = Stroke(width = strokeWidth.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}
