package com.example.data.math

/**
 * Ponto de interesse matemático a ser plotado no GeoGebra (ex: buracos de descontinuidade removível,
 * raízes, máximos/mínimos ou ponto de tangência).
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
 * Configuração analítica do GeoGebra Applet para cada aula de Cálculo.
 * Conecta-se diretamente à API GeoGebra (deployggb.js / GGBApplet) para renderizar
 * gráficos dinâmicos de alta precisão, permitindo manipulação de funções, zoom e exploração.
 */
data class GeoGebraGraphConfig(
    val title: String,
    val functionExpression: String,
    val commands: List<String>,
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
    val integralB: Double = 2.0
)
