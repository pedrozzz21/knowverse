package com.example.ui.components

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.math.GeoGebraGraphConfig
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import org.json.JSONArray
import org.json.JSONObject

/**
 * Visualizador de Gráficos Matemáticos de Alta Fidelidade com suporte a GeoGebra API.
 * Renderiza de forma instantânea e 100% autônoma (offline) funções contínuas, descontinuidades com
 * buracos abertos, assíntotas verticais e horizontais, retas tangentes com derivada em tempo real,
 * e áreas sob a curva (somas de Riemann), sem travamento ou spinners permanentes.
 */
@Composable
fun GeoGebraMathGraphView(
    config: GeoGebraGraphConfig,
    modifier: Modifier = Modifier,
    isFullScreenMode: Boolean = false,
    onToggleFullScreen: (() -> Unit)? = null
) {
    var activeExpression by remember(config) { mutableStateOf(config.functionExpression) }
    var tangentX by remember(config) { mutableDoubleStateOf(1.0) }
    var isCustomizing by remember { mutableStateOf(false) }
    var customInputText by remember { mutableStateOf(config.functionExpression) }
    var webViewRef by remember { mutableStateOf<WebView?>(null) }
    var isFullScreenDialogVisible by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header do Gráfico com identificador matemático
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
                            .size(28.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF0F766E).copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShowChart,
                            contentDescription = null,
                            tint = Color(0xFF2DD4BF),
                            modifier = Modifier.size(16.dp)
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
                                color = Color(0xFF1E3A8A).copy(alpha = 0.4f),
                                shape = RoundedCornerShape(4.dp),
                                border = androidx.compose.foundation.BorderStroke(0.6.dp, Color(0xFF60A5FA).copy(alpha = 0.5f))
                            ) {
                                Text(
                                    text = "GeoGebra API",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFF93C5FD),
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
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
                            tangentX = 1.0
                            webViewRef?.evaluateJavascript("if (window.resetView) window.resetView();", null)
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
                                    val safeExpr = JSONObject.quote(activeExpression)
                                    webViewRef?.evaluateJavascript(
                                        "if (window.updateFunction) window.updateFunction($safeExpr);",
                                        null
                                    )
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
                                val safeExpr = JSONObject.quote(preset)
                                webViewRef?.evaluateJavascript(
                                    "if (window.updateFunction) window.updateFunction($safeExpr);",
                                    null
                                )
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

            // Slider interativo de Reta Tangente (se a aula abordar derivadas)
            if (config.showTangentSlider) {
                Surface(
                    color = SurfaceElevated,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
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
                                    text = "Ponto de Tangência: x₀ = ${"%.2f".format(tangentX)}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = TextPrimary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Text(
                                text = "Arraste para mover",
                                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 10.sp)
                            )
                        }
                        Slider(
                            value = tangentX.toFloat(),
                            onValueChange = { newVal ->
                                tangentX = newVal.toDouble()
                                webViewRef?.evaluateJavascript(
                                    "if (window.setTangentPoint) window.setTangentPoint($tangentX);",
                                    null
                                )
                            },
                            valueRange = (config.xMin + 0.5).toFloat()..(config.xMax - 0.5).toFloat(),
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

            Spacer(modifier = Modifier.height(6.dp))

            // Container do Visualizador Matemático Nativo
            val graphHeight = if (isFullScreenMode) 480.dp else 260.dp
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(graphHeight)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF0B1120))
                    .border(1.dp, BorderSubtle.copy(alpha = 0.8f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                NativeMathGraphCanvas(
                    config = config,
                    activeExpression = activeExpression,
                    tangentX = tangentX,
                    modifier = Modifier.fillMaxSize()
                )
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
                                text = "Fundamentação Gráfica GeoGebra:",
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
                color = Color(0xFF0B1120)
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
                                text = "GeoGebra Lab: ${config.title}",
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

@Composable
fun NativeMathGraphCanvas(
    config: GeoGebraGraphConfig,
    activeExpression: String,
    tangentX: Double,
    modifier: Modifier = Modifier
) {
    var curXMin by remember(config.title, activeExpression) { mutableDoubleStateOf(config.xMin) }
    var curXMax by remember(config.title, activeExpression) { mutableDoubleStateOf(config.xMax) }
    var curYMin by remember(config.title, activeExpression) { mutableDoubleStateOf(config.yMin) }
    var curYMax by remember(config.title, activeExpression) { mutableDoubleStateOf(config.yMax) }

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(curXMin, curXMax, curYMin, curYMax) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        val w = size.width
                        val h = size.height
                        if (w > 0 && h > 0) {
                            val xSpan = curXMax - curXMin
                            val ySpan = curYMax - curYMin
                            val dx = (dragAmount.x / w) * xSpan
                            val dy = (dragAmount.y / h) * ySpan
                            curXMin -= dx
                            curXMax -= dx
                            curYMin += dy
                            curYMax += dy
                        }
                    }
                }
        ) {
            val w = size.width
            val h = size.height
            if (w <= 0f || h <= 0f) return@Canvas

            fun toPxX(x: Double): Float = (((x - curXMin) / (curXMax - curXMin)) * w).toFloat()
            fun toPxY(y: Double): Float = (h - ((y - curYMin) / (curYMax - curYMin)) * h).toFloat()

            // Fundo escuro profundo
            drawRect(Color(0xFF0B1120))

            // Passo de grade inteligente
            val xSpan = maxOf(0.1, curXMax - curXMin)
            val ySpan = maxOf(0.1, curYMax - curYMin)
            fun getNiceStep(range: Double): Double {
                val raw = range / 8.0
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

            // Linhas de Grade
            val gridColor = Color(0x18FFFFFF)
            var gx = floor(curXMin / xStep) * xStep
            while (gx <= curXMax) {
                val px = toPxX(gx)
                drawLine(
                    color = gridColor,
                    start = Offset(px, 0f),
                    end = Offset(px, h),
                    strokeWidth = 1f
                )
                gx += xStep
            }

            var gy = floor(curYMin / yStep) * yStep
            while (gy <= curYMax) {
                val py = toPxY(gy)
                drawLine(
                    color = gridColor,
                    start = Offset(0f, py),
                    end = Offset(w, py),
                    strokeWidth = 1f
                )
                gy += yStep
            }

            // Eixos Cartesiano (X e Y)
            val axisColor = Color(0xAA94A3B8)
            val p0X = toPxX(0.0)
            val p0Y = toPxY(0.0)
            val xAxisY = p0Y.coerceIn(16f, h - 16f)
            val yAxisX = p0X.coerceIn(22f, w - 22f)

            // Eixo X
            drawLine(
                color = axisColor,
                start = Offset(0f, xAxisY),
                end = Offset(w, xAxisY),
                strokeWidth = 1.8f
            )
            // Seta X
            val arrowPathX = Path().apply {
                moveTo(w - 2f, xAxisY)
                lineTo(w - 10f, xAxisY - 4f)
                lineTo(w - 10f, xAxisY + 4f)
                close()
            }
            drawPath(arrowPathX, axisColor)

            // Eixo Y
            drawLine(
                color = axisColor,
                start = Offset(yAxisX, 0f),
                end = Offset(yAxisX, h),
                strokeWidth = 1.8f
            )
            // Seta Y
            val arrowPathY = Path().apply {
                moveTo(yAxisX, 2f)
                lineTo(yAxisX - 4f, 10f)
                lineTo(yAxisX + 4f, 10f)
                close()
            }
            drawPath(arrowPathY, axisColor)

            // Rótulos numéricos nos eixos
            drawIntoCanvas { canvas ->
                val paint = android.graphics.Paint().apply {
                    color = 0xAA94A3B8.toInt()
                    textSize = 24f
                    typeface = android.graphics.Typeface.MONOSPACE
                    isAntiAlias = true
                }

                // X labels
                var lx = floor(curXMin / xStep) * xStep
                while (lx <= curXMax) {
                    if (abs(lx) > 0.001) {
                        val px = toPxX(lx)
                        val text = if (abs(lx - round(lx)) < 0.001) lx.toInt().toString() else "%.1f".format(lx)
                        canvas.nativeCanvas.drawText(text, px - 12f, xAxisY + 22f, paint)
                    }
                    lx += xStep
                }

                // Y labels
                var ly = floor(curYMin / yStep) * yStep
                while (ly <= curYMax) {
                    if (abs(ly) > 0.001) {
                        val py = toPxY(ly)
                        val text = if (abs(ly - round(ly)) < 0.001) ly.toInt().toString() else "%.1f".format(ly)
                        canvas.nativeCanvas.drawText(text, yAxisX + 6f, py + 8f, paint)
                    }
                    ly += yStep
                }

                // Labels dos eixos 'x' e 'y'
                val axisLabelPaint = android.graphics.Paint().apply {
                    color = 0xFF93C5FD.toInt()
                    textSize = 28f
                    typeface = android.graphics.Typeface.DEFAULT_BOLD
                    isAntiAlias = true
                }
                canvas.nativeCanvas.drawText("x", w - 24f, xAxisY - 8f, axisLabelPaint)
                canvas.nativeCanvas.drawText("y", yAxisX + 12f, 28f, axisLabelPaint)
            }

            // Área sob a curva (se ativada, ex: integrais)
            if (config.showAreaUnderCurve) {
                val areaPath = Path()
                val a = config.integralA
                val b = config.integralB
                val areaSteps = 100
                val dArea = (b - a) / areaSteps
                var started = false
                val startPx = toPxX(a)
                val basePy = toPxY(0.0)
                areaPath.moveTo(startPx, basePy)
                for (ai in 0..areaSteps) {
                    val cx = a + ai * dArea
                    val cy = MathExpressionEvaluator.evaluate(activeExpression, cx)
                    if (!cy.isNaN()) {
                        areaPath.lineTo(toPxX(cx), toPxY(cy))
                        started = true
                    }
                }
                if (started) {
                    areaPath.lineTo(toPxX(b), basePy)
                    areaPath.close()
                    drawPath(areaPath, Color(0x3838BDF8))
                }
            }

            // Assíntotas do config / commands (x = c ou y = c)
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
                            strokeWidth = 2f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f)
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
                            strokeWidth = 2f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f)
                        )
                    }
                }
            }

            // Curva da Função f(x)
            val curvePath = Path()
            val steps = 350
            val dx = xSpan / steps
            var drawing = false
            var prevY = Double.NaN

            for (si in 0..steps) {
                val cx = curXMin + si * dx
                val cy = MathExpressionEvaluator.evaluate(activeExpression, cx)

                // Detecção de descontinuidade vertical assintótica
                val isDiscontinuous = !prevY.isNaN() && !cy.isNaN() && abs(cy - prevY) > ySpan * 0.8
                if (cy.isNaN() || abs(cy) > 250 || isDiscontinuous) {
                    drawing = false
                    prevY = cy
                    continue
                }

                val px = toPxX(cx)
                val py = toPxY(cy)

                if (!drawing) {
                    curvePath.moveTo(px, py)
                    drawing = true
                } else {
                    curvePath.lineTo(px, py)
                }
                prevY = cy
            }

            drawPath(
                path = curvePath,
                color = Color(0xFF38BDF8),
                style = Stroke(
                    width = 3.2.dp.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )

            // Reta Tangente (se ativado)
            if (config.showTangentSlider) {
                var y0 = MathExpressionEvaluator.evaluate(activeExpression, tangentX)
                if (y0.isNaN()) {
                    y0 = (MathExpressionEvaluator.evaluate(activeExpression, tangentX + 0.001) +
                          MathExpressionEvaluator.evaluate(activeExpression, tangentX - 0.001)) / 2.0
                }
                if (!y0.isNaN()) {
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
                        // Ponto T0
                        drawCircle(
                            color = Color(0xFFFACC15),
                            radius = 6.dp.toPx(),
                            center = Offset(toPxX(tangentX), toPxY(y0))
                        )
                    }
                }
            }

            // Pontos de Análise (raízes, buracos abertos, assíntotas)
            config.analysisPoints.forEach { pt ->
                val pX = toPxX(pt.x)
                val pY = toPxY(pt.y)
                val ptColor = try {
                    Color(android.graphics.Color.parseColor(pt.colorHex))
                } catch (_: Throwable) {
                    Color(0xFFEF4444)
                }

                if (pt.isOpenCircle) {
                    // Buraco aberto de descontinuidade
                    drawCircle(
                        color = Color(0xFF0B1120),
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
                        textSize = 24f
                        typeface = android.graphics.Typeface.DEFAULT_BOLD
                        isAntiAlias = true
                    }
                    val label = "${pt.label} (${pt.x}, ${pt.y})"
                    canvas.nativeCanvas.drawText(label, pX + 12f, pY - 8f, ptPaint)
                }
            }
        }

        // Overlay: Badge da Equação (Top Left)
        Surface(
            color = Color(0xDD0F172A),
            shape = RoundedCornerShape(4.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x4D67E8F9)),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            Text(
                text = "f(x) = $activeExpression",
                color = Color(0xFF67E8F9),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        // Overlay: Toolbar de Zoom e Reset (Top Right)
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
                    Text("+", color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold, fontSize = 14.sp)
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
                    Text("−", color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold, fontSize = 14.sp)
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
                    }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("⟲", color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}
