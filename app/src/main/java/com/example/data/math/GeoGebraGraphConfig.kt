package com.example.data.math

/**
 * Ponto notável ou de interesse matemático a ser plotado no gráfico (ex: buracos de descontinuidade removível,
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
