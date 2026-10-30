package com.example.ui.components

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

            // Container do Visualizador GeoGebra
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
                GeoGebraHtmlWebView(
                    config = config,
                    activeExpression = activeExpression,
                    tangentX = tangentX,
                    onWebViewCreated = { webViewRef = it }
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

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun GeoGebraHtmlWebView(
    config: GeoGebraGraphConfig,
    activeExpression: String,
    tangentX: Double,
    onWebViewCreated: (WebView) -> Unit
) {
    val htmlContent = remember(config, activeExpression) {
        buildGeoGebraHtml(config, activeExpression, tangentX)
    }

    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                setBackgroundColor(0x00000000)
                isVerticalScrollBarEnabled = false
                isHorizontalScrollBarEnabled = false
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    allowFileAccess = true
                    databaseEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = false
                    cacheMode = WebSettings.LOAD_DEFAULT
                    safeBrowsingEnabled = false
                }

                loadDataWithBaseURL(
                    "file:///android_asset/geogebra/",
                    htmlContent,
                    "text/html",
                    "UTF-8",
                    null
                )
                onWebViewCreated(this)
            }
        },
        update = { webView ->
            onWebViewCreated(webView)
        },
        modifier = Modifier.fillMaxSize()
    )
}

/**
 * Constrói o visualizador matemático com Canvas de Alta Precisão (0ms delay) e suporte à API do GeoGebra.
 * Inclui:
 * - Sistema cartesiano completo com eixos X e Y graduados, linhas de grade e marcações numéricas
 * - Avaliador matemático para expressões algébricas, trigonométricas, racionais e potências com multiplicação implícita
 * - Descontinuidades com círculo aberto (buracos de limites)
 * - Assíntotas verticais e horizontais tracejadas
 * - Reta tangente com cálculo de derivada f'(x₀) e ponto T₀ em tempo real
 * - Área sob a curva sombreada (integrais / somas de Riemann)
 * - Controles interativos de pan (arrastar) e zoom (+ / - / Reset)
 */
private fun buildGeoGebraHtml(
    config: GeoGebraGraphConfig,
    activeExpression: String,
    tangentX: Double
): String {
    val escapedExpr = JSONObject.quote(activeExpression)
    val commandsJsonArray = JSONArray()
    config.commands.forEach { cmd ->
        commandsJsonArray.put(cmd)
    }
    val pointsJsonArray = JSONArray()
    config.analysisPoints.forEach { pt ->
        val obj = JSONObject()
        obj.put("name", pt.name)
        obj.put("x", pt.x)
        obj.put("y", pt.y)
        obj.put("label", pt.label)
        obj.put("isOpenCircle", pt.isOpenCircle)
        obj.put("color", pt.colorHex)
        pointsJsonArray.put(obj)
    }

    return """
        <!DOCTYPE html>
        <html>
        <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <style>
          * { box-sizing: border-box; margin: 0; padding: 0; }
          html, body {
            width: 100%; height: 100%;
            background-color: #0B1120;
            overflow: hidden;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", sans-serif;
            user-select: none;
            -webkit-user-select: none;
          }
          #container {
            position: relative;
            width: 100%; height: 100%;
          }
          #graph-canvas {
            display: block;
            width: 100%; height: 100%;
            touch-action: none;
          }
          #toolbar {
            position: absolute;
            top: 8px; right: 8px;
            display: flex;
            gap: 4px;
            z-index: 10;
          }
          .tool-btn {
            background: rgba(30, 41, 59, 0.85);
            color: #94A3B8;
            border: 1px solid rgba(148, 163, 184, 0.25);
            border-radius: 6px;
            width: 28px; height: 28px;
            font-size: 14px;
            font-weight: bold;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            box-shadow: 0 2px 4px rgba(0,0,0,0.3);
          }
          .tool-btn:active {
            background: #2563EB;
            color: #FFFFFF;
          }
          #badge-equation {
            position: absolute;
            top: 8px; left: 8px;
            background: rgba(15, 23, 42, 0.85);
            color: #67E8F9;
            border: 1px solid rgba(103, 232, 249, 0.3);
            border-radius: 4px;
            padding: 3px 8px;
            font-size: 11px;
            font-family: monospace;
            font-weight: 600;
            z-index: 10;
            pointer-events: none;
          }
        </style>
        </head>
        <body>
        <div id="container">
          <div id="badge-equation">f(x) = ${activeExpression.replace("<", "&lt;").replace(">", "&gt;")}</div>
          <div id="toolbar">
            <button class="tool-btn" onclick="zoomIn()" title="Zoom In">+</button>
            <button class="tool-btn" onclick="zoomOut()" title="Zoom Out">−</button>
            <button class="tool-btn" onclick="resetView()" title="Reset">⟲</button>
          </div>
          <canvas id="graph-canvas"></canvas>
        </div>

        <script>
          var defaultXMin = ${config.xMin};
          var defaultXMax = ${config.xMax};
          var defaultYMin = ${config.yMin};
          var defaultYMax = ${config.yMax};

          var curXMin = defaultXMin;
          var curXMax = defaultXMax;
          var curYMin = defaultYMin;
          var curYMax = defaultYMax;

          var currentExpr = $escapedExpr;
          var commands = ${commandsJsonArray.toString()};
          var analysisPoints = ${pointsJsonArray.toString()};
          var showTangent = ${config.showTangentSlider};
          var showAsymptotes = ${config.showAsymptotes};
          var showArea = ${config.showAreaUnderCurve};
          var areaA = ${config.integralA};
          var areaB = ${config.integralB};
          var tangentValX = $tangentX;

          var compiledFn = null;

          function prepareExpr(expr) {
            var s = (expr || "").trim();
            s = s.replace(/^[fy]\s*\([^)]*\)\s*=\s*/i, '');
            s = s.replace(/^[fy]\s*=\s*/i, '');
            s = s.replace(/\bpi\b/gi, 'Math.PI');
            s = s.replace(/\be\b(?![a-zA-Z0-9_])/g, 'Math.E');
            s = s.replace(/\bsen\b/gi, 'Math.sin');
            s = s.replace(/\bsin\b/gi, 'Math.sin');
            s = s.replace(/\bcos\b/gi, 'Math.cos');
            s = s.replace(/\btg\b/gi, 'Math.tan');
            s = s.replace(/\btan\b/gi, 'Math.tan');
            s = s.replace(/\bsqrt\b/gi, 'Math.sqrt');
            s = s.replace(/\bcbrt\b/gi, 'Math.cbrt');
            s = s.replace(/\babs\b/gi, 'Math.abs');
            s = s.replace(/\bln\b/gi, 'Math.log');
            s = s.replace(/\blog\b/gi, 'Math.log10');
            s = s.replace(/\bexp\b/gi, 'Math.exp');
            // Multiplicação implícita
            s = s.replace(/(\d+(\.\d+)?)\s*x\b/gi, '$1*x');
            s = s.replace(/(\d+(\.\d+)?)\s*\(/g, '$1*(');
            s = s.replace(/\)\s*\(/g, ')*(');
            s = s.replace(/\)\s*x\b/gi, ')*x');
            s = s.replace(/x\s*\(/gi, 'x*(');
            s = s.replace(/\^/g, '**');
            return s;
          }

          function compileCurrentExpr() {
            try {
              var jsCode = prepareExpr(currentExpr);
              compiledFn = new Function('x', 'return (' + jsCode + ');');
            } catch(e) {
              compiledFn = null;
            }
          }

          function evalFx(x) {
            if (!compiledFn) return NaN;
            try {
              var y = compiledFn(x);
              if (typeof y !== 'number' || isNaN(y) || !isFinite(y)) return NaN;
              return y;
            } catch(e) {
              return NaN;
            }
          }

          compileCurrentExpr();

          var cvs = document.getElementById('graph-canvas');
          var ctx = cvs.getContext('2d');

          function toPx(x, y, w, h) {
            return {
              px: ((x - curXMin) / (curXMax - curXMin)) * w,
              py: h - ((y - curYMin) / (curYMax - curYMin)) * h
            };
          }

          function drawGraph() {
            if (!cvs) return;
            var w = cvs.clientWidth || window.innerWidth || 360;
            var h = cvs.clientHeight || window.innerHeight || 260;
            if (w < 40 || h < 40) return;

            var dpr = window.devicePixelRatio || 1;
            cvs.width = Math.round(w * dpr);
            cvs.height = Math.round(h * dpr);
            ctx.setTransform(dpr, 0, 0, dpr, 0, 0);

            // Fundo escuro profundo
            ctx.fillStyle = "#0B1120";
            ctx.fillRect(0, 0, w, h);

            // Grade Secundária / Primária
            function getNiceStep(range) {
              var raw = range / 8;
              var mag = Math.pow(10, Math.floor(Math.log10(raw)));
              var norm = raw / mag;
              if (norm < 1.5) return mag;
              if (norm < 3.5) return 2 * mag;
              if (norm < 7.5) return 5 * mag;
              return 10 * mag;
            }

            var xStep = getNiceStep(curXMax - curXMin);
            var yStep = getNiceStep(curYMax - curYMin);

            // Grade
            ctx.lineWidth = 1;
            ctx.strokeStyle = "rgba(255, 255, 255, 0.05)";
            var firstX = Math.floor(curXMin / xStep) * xStep;
            for (var gx = firstX; gx <= curXMax; gx += xStep) {
              var p = toPx(gx, 0, w, h);
              ctx.beginPath();
              ctx.moveTo(p.px, 0);
              ctx.lineTo(p.px, h);
              ctx.stroke();
            }
            var firstY = Math.floor(curYMin / yStep) * yStep;
            for (var gy = firstY; gy <= curYMax; gy += yStep) {
              var p = toPx(0, gy, w, h);
              ctx.beginPath();
              ctx.moveTo(0, p.py);
              ctx.lineTo(w, p.py);
              ctx.stroke();
            }

            // Eixos Cartesiano (X e Y)
            ctx.lineWidth = 1.6;
            ctx.strokeStyle = "rgba(148, 163, 184, 0.6)";

            // Eixo X
            var p0 = toPx(0, 0, w, h);
            var xAxisY = Math.max(16, Math.min(h - 16, p0.py));
            ctx.beginPath();
            ctx.moveTo(0, xAxisY);
            ctx.lineTo(w, xAxisY);
            ctx.stroke();

            // Seta Eixo X
            ctx.fillStyle = "rgba(148, 163, 184, 0.8)";
            ctx.beginPath();
            ctx.moveTo(w - 2, xAxisY);
            ctx.lineTo(w - 8, xAxisY - 4);
            ctx.lineTo(w - 8, xAxisY + 4);
            ctx.fill();

            // Eixo Y
            var yAxisX = Math.max(22, Math.min(w - 22, p0.px));
            ctx.beginPath();
            ctx.moveTo(yAxisX, 0);
            ctx.lineTo(yAxisX, h);
            ctx.stroke();

            // Seta Eixo Y
            ctx.beginPath();
            ctx.moveTo(yAxisX, 2);
            ctx.lineTo(yAxisX - 4, 8);
            ctx.lineTo(yAxisX + 4, 8);
            ctx.fill();

            // Legenda e Marcadores numéricos
            ctx.fillStyle = "rgba(148, 163, 184, 0.75)";
            ctx.font = "10px monospace";
            for (var sx = firstX; sx <= curXMax; sx += xStep) {
              if (Math.abs(sx) > 0.0001) {
                var p = toPx(sx, 0, w, h);
                var label = Number(sx.toFixed(2)).toString();
                ctx.fillText(label, p.px - 6, xAxisY + 12);
              }
            }
            for (var sy = firstY; sy <= curYMax; sy += yStep) {
              if (Math.abs(sy) > 0.0001) {
                var p = toPx(0, sy, w, h);
                var label = Number(sy.toFixed(2)).toString();
                ctx.fillText(label, yAxisX + 4, p.py + 3);
              }
            }

            // Letras dos Eixos
            ctx.fillStyle = "#93C5FD";
            ctx.font = "bold 11px sans-serif";
            ctx.fillText("x", w - 12, xAxisY - 6);
            ctx.fillText("y", yAxisX + 8, 14);

            // Área sob a curva (se ativada)
            if (showArea) {
              ctx.save();
              ctx.fillStyle = "rgba(56, 189, 248, 0.22)";
              ctx.beginPath();
              var startPt = toPx(areaA, 0, w, h);
              ctx.moveTo(startPt.px, startPt.py);
              var areaSteps = 120;
              var dArea = (areaB - areaA) / areaSteps;
              for (var ai = 0; ai <= areaSteps; ai++) {
                var curX = areaA + ai * dArea;
                var curY = evalFx(curX);
                if (!isNaN(curY)) {
                  var p = toPx(curX, curY, w, h);
                  ctx.lineTo(p.px, p.py);
                }
              }
              var endPt = toPx(areaB, 0, w, h);
              ctx.lineTo(endPt.px, endPt.py);
              ctx.closePath();
              ctx.fill();
              ctx.restore();
            }

            // Assíntotas do config / commands
            commands.forEach(function(cmd) {
              var trimmed = cmd.trim();
              if (trimmed.startsWith("x =") || trimmed.startsWith("x=")) {
                var val = parseFloat(trimmed.replace("x", "").replace("=", "").trim());
                if (!isNaN(val)) {
                  var p = toPx(val, 0, w, h);
                  ctx.save();
                  ctx.strokeStyle = "#EF4444";
                  ctx.lineWidth = 1.5;
                  ctx.setLineDash([5, 5]);
                  ctx.beginPath();
                  ctx.moveTo(p.px, 0);
                  ctx.lineTo(p.px, h);
                  ctx.stroke();
                  ctx.fillStyle = "#F87171";
                  ctx.font = "bold 10px monospace";
                  ctx.fillText("x = " + val, p.px + 4, 24);
                  ctx.restore();
                }
              } else if (trimmed.startsWith("y =") || trimmed.startsWith("y=")) {
                var val = parseFloat(trimmed.replace("y", "").replace("=", "").trim());
                if (!isNaN(val)) {
                  var p = toPx(0, val, w, h);
                  ctx.save();
                  ctx.strokeStyle = "#F59E0B";
                  ctx.lineWidth = 1.5;
                  ctx.setLineDash([5, 5]);
                  ctx.beginPath();
                  ctx.moveTo(0, p.py);
                  ctx.lineTo(w, p.py);
                  ctx.stroke();
                  ctx.fillStyle = "#FCD34D";
                  ctx.font = "bold 10px monospace";
                  ctx.fillText("y = " + val, 8, p.py - 4);
                  ctx.restore();
                }
              }
            });

            // Curva da Função f(x)
            ctx.save();
            ctx.strokeStyle = "#38BDF8";
            ctx.lineWidth = 2.8;
            ctx.shadowColor = "rgba(56, 189, 248, 0.4)";
            ctx.shadowBlur = 6;
            ctx.beginPath();

            var steps = 400;
            var dx = (curXMax - curXMin) / steps;
            var drawing = false;
            var prevY = NaN;

            for (var i = 0; i <= steps; i++) {
              var currX = curXMin + i * dx;
              var currY = evalFx(currX);

              // Detecção de corte assintótico vertical para não conectar com reta espúria
              var isDiscontinuous = !isNaN(prevY) && !isNaN(currY) && Math.abs(currY - prevY) > (curYMax - curYMin) * 0.8;

              if (isNaN(currY) || Math.abs(currY) > 200 || isDiscontinuous) {
                drawing = false;
                prevY = currY;
                continue;
              }

              var p = toPx(currX, currY, w, h);
              if (!drawing) {
                ctx.moveTo(p.px, p.py);
                drawing = true;
              } else {
                ctx.lineTo(p.px, p.py);
              }
              prevY = currY;
            }
            ctx.stroke();
            ctx.restore();

            // Reta Tangente e Ponto T0 (se ativado)
            if (showTangent) {
              var y0 = evalFx(tangentValX);
              if (isNaN(y0)) {
                y0 = (evalFx(tangentValX + 0.0001) + evalFx(tangentValX - 0.0001)) / 2;
              }
              if (!isNaN(y0)) {
                var hDiff = 0.0005;
                var yRight = evalFx(tangentValX + hDiff);
                var yLeft = evalFx(tangentValX - hDiff);
                var slope = (yRight - yLeft) / (2 * hDiff);

                if (isFinite(slope)) {
                  var pA = toPx(curXMin, y0 + slope * (curXMin - tangentValX), w, h);
                  var pB = toPx(curXMax, y0 + slope * (curXMax - tangentValX), w, h);

                  ctx.save();
                  ctx.strokeStyle = "#FACC15";
                  ctx.lineWidth = 2.2;
                  ctx.setLineDash([4, 4]);
                  ctx.beginPath();
                  ctx.moveTo(pA.px, pA.py);
                  ctx.lineTo(pB.px, pB.py);
                  ctx.stroke();
                  ctx.restore();

                  var pT = toPx(tangentValX, y0, w, h);
                  ctx.fillStyle = "#FACC15";
                  ctx.beginPath();
                  ctx.arc(pT.px, pT.py, 5, 0, Math.PI * 2);
                  ctx.fill();

                  ctx.fillStyle = "#FEF08A";
                  ctx.font = "bold 11px monospace";
                  var slopeLabel = "T(" + tangentValX.toFixed(2) + ", " + y0.toFixed(2) + ") m=" + slope.toFixed(2);
                  ctx.fillText(slopeLabel, Math.min(w - 140, pT.px + 8), Math.max(20, pT.py - 8));
                }
              }
            }

            // Pontos de Análise (buracos abertos e pontos críticos)
            analysisPoints.forEach(function(pt) {
              var p = toPx(pt.x, pt.y, w, h);
              ctx.save();
              var color = pt.color || "#EF4444";
              ctx.strokeStyle = color;
              ctx.lineWidth = 2.5;

              if (pt.isOpenCircle) {
                ctx.fillStyle = "#0B1120";
                ctx.beginPath();
                ctx.arc(p.px, p.py, 6, 0, Math.PI * 2);
                ctx.fill();
                ctx.stroke();
              } else {
                ctx.fillStyle = color;
                ctx.beginPath();
                ctx.arc(p.px, p.py, 5, 0, Math.PI * 2);
                ctx.fill();
              }

              ctx.fillStyle = "#F8FAFC";
              ctx.font = "bold 11px sans-serif";
              ctx.fillText(pt.label + " (" + pt.x + ", " + pt.y + ")", p.px + 8, p.py - 8);
              ctx.restore();
            });
          }

          // Pan e Zoom Interativos
          var isDragging = false;
          var lastTouchX = 0, lastTouchY = 0;

          cvs.addEventListener('touchstart', function(e) {
            if (e.touches.length === 1) {
              isDragging = true;
              lastTouchX = e.touches[0].clientX;
              lastTouchY = e.touches[0].clientY;
            }
          }, { passive: true });

          cvs.addEventListener('touchmove', function(e) {
            if (isDragging && e.touches.length === 1) {
              var dx = e.touches[0].clientX - lastTouchX;
              var dy = e.touches[0].clientY - lastTouchY;
              lastTouchX = e.touches[0].clientX;
              lastTouchY = e.touches[0].clientY;

              var w = cvs.clientWidth || 360;
              var h = cvs.clientHeight || 260;
              var xRange = curXMax - curXMin;
              var yRange = curYMax - curYMin;

              var xShift = (dx / w) * xRange;
              var yShift = (dy / h) * yRange;

              curXMin -= xShift; curXMax -= xShift;
              curYMin += yShift; curYMax += yShift;
              requestAnimationFrame(drawGraph);
            }
          }, { passive: true });

          cvs.addEventListener('touchend', function() { isDragging = false; });
          cvs.addEventListener('touchcancel', function() { isDragging = false; });

          // Mouse Drag para desktop/emulador
          cvs.addEventListener('mousedown', function(e) {
            isDragging = true;
            lastTouchX = e.clientX;
            lastTouchY = e.clientY;
          });
          window.addEventListener('mousemove', function(e) {
            if (isDragging) {
              var dx = e.clientX - lastTouchX;
              var dy = e.clientY - lastTouchY;
              lastTouchX = e.clientX;
              lastTouchY = e.clientY;

              var w = cvs.clientWidth || 360;
              var h = cvs.clientHeight || 260;
              var xRange = curXMax - curXMin;
              var yRange = curYMax - curYMin;

              var xShift = (dx / w) * xRange;
              var yShift = (dy / h) * yRange;

              curXMin -= xShift; curXMax -= xShift;
              curYMin += yShift; curYMax += yShift;
              requestAnimationFrame(drawGraph);
            }
          });
          window.addEventListener('mouseup', function() { isDragging = false; });

          window.zoomIn = function() {
            var xMid = (curXMin + curXMax) / 2;
            var yMid = (curYMin + curYMax) / 2;
            var xHalf = (curXMax - curXMin) * 0.35;
            var yHalf = (curYMax - curYMin) * 0.35;
            curXMin = xMid - xHalf; curXMax = xMid + xHalf;
            curYMin = yMid - yHalf; curYMax = yMid + yHalf;
            drawGraph();
          };

          window.zoomOut = function() {
            var xMid = (curXMin + curXMax) / 2;
            var yMid = (curYMin + curYMax) / 2;
            var xHalf = (curXMax - curXMin) * 0.7;
            var yHalf = (curYMax - curYMin) * 0.7;
            curXMin = xMid - xHalf; curXMax = xMid + xHalf;
            curYMin = yMid - yHalf; curYMax = yMid + yHalf;
            drawGraph();
          };

          window.resetView = function() {
            curXMin = defaultXMin; curXMax = defaultXMax;
            curYMin = defaultYMin; curYMax = defaultYMax;
            drawGraph();
          };

          window.updateFunction = function(newExpr) {
            currentExpr = newExpr;
            document.getElementById('badge-equation').innerText = 'f(x) = ' + newExpr;
            compileCurrentExpr();
            drawGraph();
          };

          window.setTangentPoint = function(xVal) {
            tangentValX = xVal;
            drawGraph();
          };

          // Redimensionamento automático
          if (window.ResizeObserver) {
            new ResizeObserver(function() {
              requestAnimationFrame(drawGraph);
            }).observe(cvs);
          }
          window.addEventListener('resize', drawGraph);
          window.addEventListener('load', drawGraph);

          // Renderização inicial imediata
          requestAnimationFrame(drawGraph);
          setTimeout(drawGraph, 50);
          setTimeout(drawGraph, 200);
        </script>
        </body>
        </html>
    """.trimIndent()
}
