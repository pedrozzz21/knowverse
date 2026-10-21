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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import org.json.JSONArray
import org.json.JSONObject

/**
 * Componente oficial de visualização matemática interativa com a API do GeoGebra (GGBApplet).
 * Permite explorar funções analíticas, limites, descontinuidades com buraco, assíntotas,
 * retas tangentes e somas de Riemann em tempo real.
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
    var isAppletReady by remember { mutableStateOf(false) }
    var webViewRef by remember { mutableStateOf<WebView?>(null) }
    var isFullScreenDialogVisible by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header do Gráfico com Badge GeoGebra Oficial
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
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
                        text = "Digite qualquer expressão matemática para plotar no GeoGebra:",
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
                            valueRange = (config.xMin + 1.0).toFloat()..(config.xMax - 1.0).toFloat(),
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
                    onWebViewCreated = { webViewRef = it },
                    onAppletReady = { isAppletReady = true }
                )

                if (!isAppletReady) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF0B1120)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(
                                color = Color(0xFF67E8F9),
                                strokeWidth = 2.5.dp,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Carregando GeoGebra Math Engine...",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary,
                                    fontSize = 11.sp
                                )
                            )
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
                            modifier = Modifier.fillMaxWidth(),
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
    onWebViewCreated: (WebView) -> Unit,
    onAppletReady: () -> Unit
) {
    val context = LocalContext.current

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
                }

                addJavascriptInterface(object {
                    @JavascriptInterface
                    fun onReady() {
                        Handler(Looper.getMainLooper()).post {
                            onAppletReady()
                        }
                    }
                }, "AndroidBridge")

                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        Handler(Looper.getMainLooper()).postDelayed({
                            onAppletReady()
                        }, 500)
                    }
                }

                loadDataWithBaseURL(
                    "https://www.geogebra.org/",
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
 * Constrói a página HTML completa com o wrapper GeoGebra (deployggb.js / GGBApplet),
 * além do fallback Canvas de alta precisão que garante renderização instantânea
 * mesmo sob conexões lentas ou offline.
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
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
          }
          #container {
            position: relative; width: 100%; height: 100%;
            display: flex; justify-content: center; align-items: center;
          }
          #ggb-element {
            width: 100%; height: 100%;
          }
          #canvas-fallback {
            position: absolute; top: 0; left: 0; width: 100%; height: 100%;
            display: block;
          }
        </style>
        <script src="https://www.geogebra.org/apps/deployggb.js"></script>
        </head>
        <body>
        <div id="container">
          <canvas id="canvas-fallback"></canvas>
          <div id="ggb-element"></div>
        </div>

        <script>
          var ggbApi = null;
          var xMin = ${config.xMin};
          var xMax = ${config.xMax};
          var yMin = ${config.yMin};
          var yMax = ${config.yMax};
          var currentExpr = $escapedExpr;
          var initialCommands = ${commandsJsonArray.toString()};
          var analysisPoints = ${pointsJsonArray.toString()};
          var showTangent = ${config.showTangentSlider};
          var showAsymptotes = ${config.showAsymptotes};
          var showArea = ${config.showAreaUnderCurve};
          var areaA = ${config.integralA};
          var areaB = ${config.integralB};
          var tangentValX = $tangentX;

          // Desenho no Canvas Fallback Instantâneo (Offline/Rápido)
          function drawFallback() {
            var cvs = document.getElementById('canvas-fallback');
            if (!cvs) return;
            var w = cvs.clientWidth || window.innerWidth;
            var h = cvs.clientHeight || window.innerHeight;
            cvs.width = w; cvs.height = h;
            var ctx = cvs.getContext('2d');
            if (!ctx) return;

            ctx.fillStyle = "#0B1120";
            ctx.fillRect(0, 0, w, h);

            // Grade Cartesiana
            ctx.strokeStyle = "rgba(255, 255, 255, 0.07)";
            ctx.lineWidth = 1;
            function toX(val) { return ((val - xMin) / (xMax - xMin)) * w; }
            function toY(val) { return h - ((val - yMin) / (yMax - yMin)) * h; }

            for (var x = Math.ceil(xMin); x <= Math.floor(xMax); x++) {
              ctx.beginPath();
              ctx.moveTo(toX(x), 0); ctx.lineTo(toX(x), h);
              ctx.stroke();
            }
            for (var y = Math.ceil(yMin); y <= Math.floor(yMax); y++) {
              ctx.beginPath();
              ctx.moveTo(0, toY(y)); ctx.lineTo(w, toY(y));
              ctx.stroke();
            }

            // Eixos X e Y
            ctx.strokeStyle = "rgba(148, 163, 184, 0.5)";
            ctx.lineWidth = 1.5;
            // Eixo X
            if (yMin <= 0 && yMax >= 0) {
              var y0 = toY(0);
              ctx.beginPath(); ctx.moveTo(0, y0); ctx.lineTo(w, y0); ctx.stroke();
            }
            // Eixo Y
            if (xMin <= 0 && xMax >= 0) {
              var x0 = toX(0);
              ctx.beginPath(); ctx.moveTo(x0, 0); ctx.lineTo(x0, h); ctx.stroke();
            }

            // Marcadores de escala numéricos
            ctx.fillStyle = "rgba(148, 163, 184, 0.7)";
            ctx.font = "10px monospace";
            for (var sx = Math.ceil(xMin); sx <= Math.floor(xMax); sx++) {
              if (sx !== 0) ctx.fillText(sx, toX(sx) - 4, toY(0) + 14);
            }
            for (var sy = Math.ceil(yMin); sy <= Math.floor(yMax); sy++) {
              if (sy !== 0) ctx.fillText(sy, toX(0) + 5, toY(sy) + 3);
            }

            // Avaliador de expressões matemáticas simples para plot seguro
            function evalFx(x) {
              try {
                var expr = currentExpr
                  .replace(/sin/g, "Math.sin")
                  .replace(/cos/g, "Math.cos")
                  .replace(/tan/g, "Math.tan")
                  .replace(/sqrt/g, "Math.sqrt")
                  .replace(/abs/g, "Math.abs")
                  .replace(/exp/g, "Math.exp")
                  .replace(/\^/g, "**");
                // proteção contra divisão por zero exata
                return Function("x", "return " + expr)(x);
              } catch(e) {
                return NaN;
              }
            }

            // Área sob a curva (se ativada)
            if (showArea) {
              ctx.fillStyle = "rgba(56, 189, 248, 0.18)";
              ctx.beginPath();
              var startPx = toX(areaA);
              ctx.moveTo(startPx, toY(0));
              var step = (areaB - areaA) / 80;
              for (var cur = areaA; cur <= areaB; cur += step) {
                var yVal = evalFx(cur);
                if (!isNaN(yVal)) ctx.lineTo(toX(cur), toY(yVal));
              }
              ctx.lineTo(toX(areaB), toY(0));
              ctx.closePath();
              ctx.fill();
            }

            // Plot da Curva da Função
            ctx.strokeStyle = "#38BDF8";
            ctx.lineWidth = 3;
            ctx.beginPath();
            var started = false;
            var numSteps = 300;
            var dx = (xMax - xMin) / numSteps;

            for (var i = 0; i <= numSteps; i++) {
              var currX = xMin + i * dx;
              var currY = evalFx(currX);
              if (isNaN(currY) || !isFinite(currY) || Math.abs(currY) > 50) {
                started = false;
                continue;
              }
              var px = toX(currX);
              var py = toY(currY);
              if (!started) {
                ctx.moveTo(px, py);
                started = true;
              } else {
                ctx.lineTo(px, py);
              }
            }
            ctx.stroke();

            // Plot de Pontos de Análise (buracos ou valores críticos)
            analysisPoints.forEach(function(pt) {
              var px = toX(pt.x);
              var py = toY(pt.y);
              ctx.fillStyle = pt.color || "#EF4444";
              ctx.strokeStyle = pt.color || "#EF4444";
              ctx.lineWidth = 2.5;

              if (pt.isOpenCircle) {
                ctx.fillStyle = "#0B1120";
                ctx.beginPath();
                ctx.arc(px, py, 6, 0, Math.PI * 2);
                ctx.fill();
                ctx.stroke();
              } else {
                ctx.beginPath();
                ctx.arc(px, py, 5, 0, Math.PI * 2);
                ctx.fill();
              }

              // Rótulo do Ponto
              ctx.fillStyle = "#F8FAFC";
              ctx.font = "bold 11px sans-serif";
              ctx.fillText(pt.label + " (" + pt.x + ", " + pt.y + ")", px + 8, py - 8);
            });
          }

          // Execução inicial do Canvas
          window.addEventListener('load', drawFallback);
          window.addEventListener('resize', drawFallback);
          drawFallback();

          // Inicialização GeoGebra Oficial
          function initGeoGebra() {
            if (typeof GGBApplet === 'undefined') {
              console.log("GeoGebra script not yet loaded, relying on native math canvas");
              if (window.AndroidBridge) window.AndroidBridge.onReady();
              return;
            }

            var ggbContainer = document.getElementById('ggb-element');
            var width = ggbContainer.clientWidth || window.innerWidth || 360;
            var height = ggbContainer.clientHeight || 260;

            var params = {
              "appName": "graphing",
              "width": width,
              "height": height,
              "showToolBar": false,
              "showAlgebraInput": false,
              "showMenuBar": false,
              "showResetIcon": true,
              "enableLabelDrags": false,
              "enableShiftDragZoom": true,
              "enableRightClick": false,
              "errorDialogsActive": false,
              "useBrowserForJS": false,
              "borderColor": "#1E293B",
              "appletOnLoad": function(api) {
                ggbApi = api;
                document.getElementById('canvas-fallback').style.display = 'none';

                api.setCoordSystem(xMin, xMax, yMin, yMax);
                api.setAxesVisible(true, true);
                api.setGridVisible(true);

                // Executa comandos iniciais
                initialCommands.forEach(function(cmd) {
                  api.evalCommand(cmd);
                });

                // Se houver pontos com círculo aberto (buracos de descontinuidade)
                analysisPoints.forEach(function(pt) {
                  if (pt.isOpenCircle) {
                    api.setPointStyle(pt.name, 2); // 2 = círculo vazio
                    api.setPointSize(pt.name, 6);
                  }
                });

                if (window.AndroidBridge) window.AndroidBridge.onReady();
              }
            };

            var applet = new GGBApplet(params, true);
            applet.inject('ggb-element');
          }

          // Métodos expostos para controle via Compose
          window.updateFunction = function(newExpr) {
            currentExpr = newExpr;
            if (ggbApi) {
              ggbApi.evalCommand("f(x) = " + newExpr);
              ggbApi.setColor("f", 56, 189, 248);
            } else {
              drawFallback();
            }
          };

          window.resetView = function() {
            if (ggbApi) {
              ggbApi.setCoordSystem(xMin, xMax, yMin, yMax);
            } else {
              drawFallback();
            }
          };

          window.setTangentPoint = function(xVal) {
            tangentValX = xVal;
            if (ggbApi) {
              ggbApi.evalCommand("T_0 = (" + xVal + ", f(" + xVal + "))");
              ggbApi.evalCommand("t = Tangent(T_0, f)");
              ggbApi.setColor("t", 234, 179, 8);
            } else {
              drawFallback();
            }
          };

          setTimeout(function() {
            initGeoGebra();
          }, 300);
        </script>
        </body>
        </html>
    """.trimIndent()
}
