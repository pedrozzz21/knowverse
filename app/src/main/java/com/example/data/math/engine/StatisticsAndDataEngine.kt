package com.example.data.math.engine

import java.util.Locale
import kotlin.math.*

/**
 * Tipos de gráficos estatísticos suportados pelo laboratório visual do KnowVerse.
 */
enum class StatisticalChartType(val label: String) {
    BAR("Gráfico de Barras"),
    LINE("Gráfico de Linhas"),
    HISTOGRAM("Histograma deClasses"),
    SCATTER("Dispersão + Regressão"),
    PIE_SECTOR("Gráfico de Setores"),
    BOX_PLOT("Box Plot (Quartis)"),
    FREQUENCY("Polígono de Frequência")
}

/**
 * Classe/intervalo de distribuição de frequências para Histogramas e Tabelas de Frequência.
 */
data class FrequencyClassBin(
    val lowerBound: Double,
    val upperBound: Double,
    val midpoint: Double,
    val absoluteFrequency: Int,
    val relativeFrequency: Double,
    val cumulativeFrequency: Int
) {
    val label: String
        get() = String.format(Locale.US, "[%.1f, %.1f)", lowerBound, upperBound)
}

/**
 * Relatório completo de Estatística Descritiva Univariada.
 * Diferencia explicitamente variância e desvio padrão populacionais (divisão por N)
 * dos amostrais (correção de Bessel, divisão por N - 1).
 */
data class DescriptiveStatisticsSummary(
    val count: Int,
    val sortedValues: List<Double>,
    val sum: Double,
    val mean: Double,
    val median: Double,
    val modes: List<Double>,
    val modeClassification: String,
    val populationVariance: Double,
    val sampleVariance: Double,
    val populationStdDev: Double,
    val sampleStdDev: Double,
    val min: Double,
    val max: Double,
    val range: Double,
    val q1: Double,
    val q2: Double,
    val q3: Double,
    val interquartileRange: Double,
    val p10: Double,
    val p90: Double,
    val outliers: List<Double>,
    val frequencyDistribution: List<FrequencyClassBin>
)

/**
 * Relatório de Correlação de Pearson e Regressão Linear Simples (Mínimos Quadrados Ordinários).
 */
data class LinearRegressionSummary(
    val sampleSize: Int,
    val pearsonR: Double,
    val rSquared: Double,
    val slope: Double,
    val intercept: Double,
    val equationText: String,
    val correlationInterpretation: String,
    val methodologicalNote: String = "Atenção: Correlação linear mede o grau de associação numérica entre X e Y, mas NÃO implica relação de causalidade."
) {
    fun predictY(x: Double): Double = slope * x + intercept
}

/**
 * Motor de Estatística Descritiva, Distribuição de Frequências, Correlação e Regressão (Seção 7 / Subetapa 3.6).
 */
object StatisticsAndDataEngine {

    /**
     * Converte uma string de números separados por vírgula, ponto-e-vírgula ou espaço em lista de Double.
     */
    fun parseDataset(rawInput: String): List<Double> {
        if (rawInput.isBlank()) return emptyList()
        return rawInput
            .trim()
            .split(Regex("""[,;\s]+"""))
            .mapNotNull { token -> token.trim().toDoubleOrNull()?.takeIf { it.isFinite() } }
    }

    /**
     * Calcula todas as medidas de tendência central, dispersão (populacional e amostral), separatrizes,
     * valores discrepantes (Tukey) e distribuição de frequências.
     */
    fun analyzeUnivariate(data: List<Double>, customBinCount: Int? = null): DescriptiveStatisticsSummary? {
        val clean = data.filter { it.isFinite() }
        if (clean.isEmpty()) return null

        val sorted = clean.sorted()
        val n = sorted.size
        val sum = sorted.sum()
        val mean = sum / n.toDouble()

        val median = computePercentileFromSorted(sorted, 50.0)
        val q1 = computePercentileFromSorted(sorted, 25.0)
        val q3 = computePercentileFromSorted(sorted, 75.0)
        val iqr = q3 - q1
        val p10 = computePercentileFromSorted(sorted, 10.0)
        val p90 = computePercentileFromSorted(sorted, 90.0)

        // Moda (agrupada com tolerância 1e-9)
        val freqMap = LinkedHashMap<Long, MutableList<Double>>()
        for (v in sorted) {
            val key = round(v * 1e6).toLong()
            freqMap.getOrPut(key) { mutableListOf() }.add(v)
        }
        val maxFreq = freqMap.values.maxOfOrNull { it.size } ?: 1
        val modes: List<Double>
        val modeClass: String
        if (maxFreq <= 1 || freqMap.values.all { it.size == maxFreq }) {
            modes = emptyList()
            modeClass = "Amodal (sem repetição dominante)"
        } else {
            modes = freqMap.values.filter { it.size == maxFreq }.map { it.first() }
            modeClass = when (modes.size) {
                1 -> "Unimodal"
                2 -> "Bimodal"
                else -> "Multimodal (${modes.size} modas)"
            }
        }

        // Variância Populacional (σ² = Σ(x_i - μ)² / N) e Amostral (s² = Σ(x_i - x̄)² / (N - 1))
        val sumSqDiff = sorted.sumOf { (it - mean) * (it - mean) }
        val popVariance = sumSqDiff / n.toDouble()
        val sampleVariance = if (n > 1) sumSqDiff / (n - 1).toDouble() else Double.NaN
        val popStdDev = sqrt(popVariance)
        val sampleStdDev = if (n > 1) sqrt(sampleVariance) else Double.NaN

        val minVal = sorted.first()
        val maxVal = sorted.last()
        val rangeVal = maxVal - minVal

        // Critério de Tukey para valores discrepantes (outliers)
        val lowerFence = q1 - 1.5 * iqr
        val upperFence = q3 + 1.5 * iqr
        val outliers = sorted.filter { it < lowerFence - 1e-9 || it > upperFence + 1e-9 }

        val bins = buildFrequencyDistribution(sorted, customBinCount)

        return DescriptiveStatisticsSummary(
            count = n,
            sortedValues = sorted,
            sum = sum,
            mean = mean,
            median = median,
            modes = modes,
            modeClassification = modeClass,
            populationVariance = popVariance,
            sampleVariance = sampleVariance,
            populationStdDev = popStdDev,
            sampleStdDev = sampleStdDev,
            min = minVal,
            max = maxVal,
            range = rangeVal,
            q1 = q1,
            q2 = median,
            q3 = q3,
            interquartileRange = iqr,
            p10 = p10,
            p90 = p90,
            outliers = outliers,
            frequencyDistribution = bins
        )
    }

    /**
     * Calcula o percentil p ∈ [0, 100] usando interpolação linear (método R-7 / padrão científico).
     */
    fun computePercentile(values: List<Double>, percentile: Double): Double {
        if (values.isEmpty()) return Double.NaN
        return computePercentileFromSorted(values.sorted(), percentile)
    }

    private fun computePercentileFromSorted(sorted: List<Double>, percentile: Double): Double {
        if (sorted.isEmpty()) return Double.NaN
        if (sorted.size == 1) return sorted[0]
        val p = percentile.coerceIn(0.0, 100.0) / 100.0
        val pos = p * (sorted.size - 1)
        val lowerIdx = floor(pos).toInt().coerceIn(0, sorted.lastIndex)
        val upperIdx = ceil(pos).toInt().coerceIn(0, sorted.lastIndex)
        val weight = pos - lowerIdx
        return sorted[lowerIdx] * (1.0 - weight) + sorted[upperIdx] * weight
    }

    /**
     * Constrói tabela de distribuição de frequências usando a Regra de Sturges (k = ⌈1 + 3.322 log10(n)⌉).
     */
    fun buildFrequencyDistribution(sorted: List<Double>, customBinCount: Int? = null): List<FrequencyClassBin> {
        if (sorted.isEmpty()) return emptyList()
        val n = sorted.size
        val minVal = sorted.first()
        val maxVal = sorted.last()
        if (abs(maxVal - minVal) < 1e-9) {
            return listOf(
                FrequencyClassBin(
                    lowerBound = minVal - 0.5,
                    upperBound = maxVal + 0.5,
                    midpoint = minVal,
                    absoluteFrequency = n,
                    relativeFrequency = 1.0,
                    cumulativeFrequency = n
                )
            )
        }
        val k = (customBinCount ?: ceil(1.0 + 3.322 * log10(n.toDouble())).toInt()).coerceIn(3, 12)
        val width = (maxVal - minVal) / k.toDouble()
        val bins = mutableListOf<FrequencyClassBin>()
        var cumulative = 0

        for (i in 0 until k) {
            val low = minVal + i * width
            val high = if (i == k - 1) maxVal else minVal + (i + 1) * width
            val countInBin = sorted.count { v ->
                if (i == k - 1) v >= low - 1e-9 && v <= high + 1e-9
                else v >= low - 1e-9 && v < high - 1e-9
            }
            cumulative += countInBin
            bins.add(
                FrequencyClassBin(
                    lowerBound = low,
                    upperBound = high,
                    midpoint = 0.5 * (low + high),
                    absoluteFrequency = countInBin,
                    relativeFrequency = countInBin.toDouble() / n.toDouble(),
                    cumulativeFrequency = cumulative
                )
            )
        }
        return bins
    }

    /**
     * Calcula Correlação de Pearson (r), Coeficiente de Determinação (R²) e Regressão Linear Simples (y = a*x + b).
     */
    fun analyzeLinearRegression(xValues: List<Double>, yValues: List<Double>): LinearRegressionSummary? {
        val n = minOf(xValues.size, yValues.size)
        if (n < 2) return null

        val xs = xValues.take(n)
        val ys = yValues.take(n)
        val meanX = xs.average()
        val meanY = ys.average()

        var sxx = 0.0
        var syy = 0.0
        var sxy = 0.0
        for (i in 0 until n) {
            val dx = xs[i] - meanX
            val dy = ys[i] - meanY
            sxx += dx * dx
            syy += dy * dy
            sxy += dx * dy
        }

        if (sxx < 1e-12) return null

        val slope = sxy / sxx
        val intercept = meanY - slope * meanX
        val r = if (syy < 1e-12) 0.0 else (sxy / sqrt(sxx * syy)).coerceIn(-1.0, 1.0)
        val rSq = r * r

        val absR = abs(r)
        val dir = if (r >= 0) "positiva" else "negativa"
        val strength = when {
            absR >= 0.9 -> "Correlação linear $dir muito forte"
            absR >= 0.7 -> "Correlação linear $dir forte"
            absR >= 0.4 -> "Correlação linear $dir moderada"
            absR >= 0.15 -> "Correlação linear $dir fraca"
            else -> "Ausência de correlação linear significativa"
        }

        val eq = String.format(Locale.US, "ŷ = %.3fx %+.3f", slope, intercept)
        return LinearRegressionSummary(
            sampleSize = n,
            pearsonR = r,
            rSquared = rSq,
            slope = slope,
            intercept = intercept,
            equationText = eq,
            correlationInterpretation = strength
        )
    }
}

