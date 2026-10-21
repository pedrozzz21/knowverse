package com.example.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * Custom Vector Icons for Knowverse.
 * Per project requirements: NO emojis are used in the application.
 * All iconography is provided as clean, vector-based SVG graphics.
 */
object KnowverseIcons {

    val Python: ImageVector by lazy {
        ImageVector.Builder(
            name = "PythonIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            // Upper snake (Blue/Violet)
            path(fill = SolidColor(Color(0xFF38BDF8))) {
                moveTo(11.9f, 2f)
                curveTo(8.5f, 2f, 6.7f, 3.4f, 6.7f, 5.2f)
                verticalLineTo(7.1f)
                horizontalLineTo(12.1f)
                verticalLineTo(7.8f)
                horizontalLineTo(4.7f)
                curveTo(3.1f, 7.8f, 2f, 8.9f, 2f, 10.5f)
                verticalLineTo(14.2f)
                curveTo(2f, 15.6f, 3.3f, 16.7f, 4.7f, 16.7f)
                horizontalLineTo(6.3f)
                verticalLineTo(14.8f)
                curveTo(6.3f, 13.1f, 7.7f, 11.7f, 9.4f, 11.7f)
                horizontalLineTo(14.7f)
                curveTo(16.1f, 11.7f, 17.3f, 10.5f, 17.3f, 9.1f)
                verticalLineTo(5.2f)
                curveTo(17.3f, 3.4f, 15.3f, 2f, 11.9f, 2f)
                close()
                // Eye
                moveTo(9.8f, 3.9f)
                arcTo(0.8f, 0.8f, 0f, true, true, 9.8f, 5.5f)
                arcTo(0.8f, 0.8f, 0f, true, true, 9.8f, 3.9f)
                close()
            }
            // Lower snake (Gold/Amber)
            path(fill = SolidColor(Color(0xFFFBBF24))) {
                moveTo(12.1f, 22f)
                curveTo(15.5f, 22f, 17.3f, 20.6f, 17.3f, 18.8f)
                verticalLineTo(16.9f)
                horizontalLineTo(11.9f)
                verticalLineTo(16.2f)
                horizontalLineTo(19.3f)
                curveTo(20.9f, 16.2f, 22f, 15.1f, 22f, 13.5f)
                verticalLineTo(9.8f)
                curveTo(22f, 8.4f, 20.7f, 7.3f, 19.3f, 7.3f)
                horizontalLineTo(17.7f)
                verticalLineTo(9.2f)
                curveTo(17.7f, 10.9f, 16.3f, 12.3f, 14.6f, 12.3f)
                horizontalLineTo(9.3f)
                curveTo(7.9f, 12.3f, 6.7f, 13.5f, 6.7f, 14.9f)
                verticalLineTo(18.8f)
                curveTo(6.7f, 20.6f, 8.7f, 22f, 12.1f, 22f)
                close()
                // Eye
                moveTo(14.2f, 20.1f)
                arcTo(0.8f, 0.8f, 0f, true, true, 14.2f, 18.5f)
                arcTo(0.8f, 0.8f, 0f, true, true, 14.2f, 20.1f)
                close()
            }
        }.build()
    }

    val StreakFire: ImageVector by lazy {
        ImageVector.Builder(
            name = "StreakFire",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFB923C))) {
                moveTo(13.5f, 2f)
                curveTo(11f, 5.5f, 14f, 8f, 12.5f, 10.5f)
                curveTo(11.5f, 9.2f, 10.8f, 7.8f, 10.5f, 6f)
                curveTo(7.5f, 8.5f, 6f, 12f, 6f, 15f)
                curveTo(6f, 18.3f, 8.7f, 21f, 12f, 21f)
                curveTo(15.3f, 21f, 18f, 18.3f, 18f, 15f)
                curveTo(18f, 10f, 15.2f, 6.5f, 13.5f, 2f)
                close()
                // Inner core flame
                moveTo(12f, 19f)
                curveTo(10.3f, 19f, 9f, 17.7f, 9f, 16f)
                curveTo(9f, 13.5f, 11f, 12.5f, 12f, 11f)
                curveTo(13f, 12.5f, 15f, 13.5f, 15f, 16f)
                curveTo(15f, 17.7f, 13.7f, 19f, 12f, 19f)
                close()
            }
        }.build()
    }

    val FlameActive: ImageVector by lazy {
        ImageVector.Builder(
            name = "FlameActive",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            // Outer bright orange flame
            path(fill = SolidColor(Color(0xFFEA580C))) {
                moveTo(13.5f, 2f)
                curveTo(11.5f, 5f, 14f, 7.5f, 12.8f, 10f)
                curveTo(11.8f, 8.8f, 11.2f, 7.5f, 11f, 5.8f)
                curveTo(8f, 8f, 6.5f, 11.5f, 6.5f, 14.5f)
                curveTo(6.5f, 18f, 9f, 21.5f, 12.5f, 21.5f)
                curveTo(16f, 21.5f, 18.5f, 18f, 18.5f, 14.5f)
                curveTo(18.5f, 9.8f, 15.5f, 6.2f, 13.5f, 2f)
                close()
            }
            // Inner fiery amber-yellow core
            path(fill = SolidColor(Color(0xFFFBBF24))) {
                moveTo(12.5f, 19.8f)
                curveTo(10.8f, 19.8f, 9.5f, 18.5f, 9.5f, 16.8f)
                curveTo(9.5f, 14.2f, 11.5f, 13.2f, 12.5f, 11.5f)
                curveTo(13.5f, 13.2f, 15.5f, 14.2f, 15.5f, 16.8f)
                curveTo(15.5f, 18.5f, 14.2f, 19.8f, 12.5f, 19.8f)
                close()
            }
        }.build()
    }

    val FlameInactive: ImageVector by lazy {
        ImageVector.Builder(
            name = "FlameInactive",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            // Muted gray outline/dim flame
            path(fill = SolidColor(Color(0xFF64748B))) {
                moveTo(13.5f, 2f)
                curveTo(11.5f, 5f, 14f, 7.5f, 12.8f, 10f)
                curveTo(11.8f, 8.8f, 11.2f, 7.5f, 11f, 5.8f)
                curveTo(8f, 8f, 6.5f, 11.5f, 6.5f, 14.5f)
                curveTo(6.5f, 18f, 9f, 21.5f, 12.5f, 21.5f)
                curveTo(16f, 21.5f, 18.5f, 18f, 18.5f, 14.5f)
                curveTo(18.5f, 9.8f, 15.5f, 6.2f, 13.5f, 2f)
                close()
                // Hollow center
                moveTo(12.5f, 19.5f)
                curveTo(10.5f, 19.5f, 8.5f, 17.5f, 8.5f, 14.5f)
                curveTo(8.5f, 12.5f, 9.5f, 10f, 11.5f, 8f)
                curveTo(11.7f, 9.5f, 12.5f, 11f, 13.5f, 12f)
                curveTo(14.5f, 10.5f, 14.5f, 8.5f, 14f, 6f)
                curveTo(15.5f, 9.5f, 16.5f, 12f, 16.5f, 14.5f)
                curveTo(16.5f, 17.5f, 14.5f, 19.5f, 12.5f, 19.5f)
                close()
            }
        }.build()
    }

    val XpGem: ImageVector by lazy {
        ImageVector.Builder(
            name = "XpGem",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFBBF24))) {
                moveTo(6f, 3f)
                lineTo(18f, 3f)
                lineTo(22f, 9f)
                lineTo(12f, 21f)
                lineTo(2f, 9f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFDE68A))) {
                moveTo(6f, 3f)
                lineTo(9.5f, 9f)
                lineTo(14.5f, 9f)
                lineTo(18f, 3f)
                close()
                moveTo(9.5f, 9f)
                lineTo(12f, 21f)
                lineTo(14.5f, 9f)
                close()
            }
        }.build()
    }

    val Trophy: ImageVector by lazy {
        ImageVector.Builder(
            name = "Trophy",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFF59E0B))) {
                moveTo(19f, 4f)
                horizontalLineTo(17f)
                verticalLineTo(3f)
                curveTo(17f, 2.4f, 16.6f, 2f, 16f, 2f)
                horizontalLineTo(8f)
                curveTo(7.4f, 2f, 7f, 2.4f, 7f, 3f)
                verticalLineTo(4f)
                horizontalLineTo(5f)
                curveTo(3.9f, 4f, 3f, 4.9f, 3f, 6f)
                verticalLineTo(7f)
                curveTo(3f, 9.8f, 5.2f, 12f, 8f, 12f)
                horizontalLineTo(8.1f)
                curveTo(8.6f, 13.8f, 10.1f, 15.2f, 12f, 15.5f)
                verticalLineTo(18f)
                horizontalLineTo(9f)
                curveTo(8.4f, 18f, 8f, 18.4f, 8f, 19f)
                verticalLineTo(21f)
                curveTo(8f, 21.6f, 8.4f, 22f, 9f, 22f)
                horizontalLineTo(15f)
                curveTo(15.6f, 22f, 16f, 21.6f, 16f, 21f)
                verticalLineTo(19f)
                curveTo(16f, 18.4f, 15.6f, 18f, 15f, 18f)
                horizontalLineTo(12f)
                verticalLineTo(15.5f)
                curveTo(13.9f, 15.2f, 15.4f, 13.8f, 15.9f, 12f)
                horizontalLineTo(16f)
                curveTo(18.8f, 12f, 21f, 9.8f, 21f, 7f)
                verticalLineTo(6f)
                curveTo(21f, 4.9f, 20.1f, 4f, 19f, 4f)
                close()
                // Left ear
                moveTo(5f, 7f)
                verticalLineTo(6f)
                horizontalLineTo(7f)
                verticalLineTo(9.8f)
                curveTo(5.9f, 9.4f, 5f, 8.3f, 5f, 7f)
                close()
                // Right ear
                moveTo(19f, 7f)
                curveTo(19f, 8.3f, 18.1f, 9.4f, 17f, 9.8f)
                verticalLineTo(6f)
                horizontalLineTo(19f)
                verticalLineTo(7f)
                close()
            }
        }.build()
    }

    val CodeTerminal: ImageVector by lazy {
        ImageVector.Builder(
            name = "CodeTerminal",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF38BDF8))) {
                moveTo(20f, 4f)
                horizontalLineTo(4f)
                curveTo(2.9f, 4f, 2f, 4.9f, 2f, 6f)
                verticalLineTo(18f)
                curveTo(2f, 19.1f, 2.9f, 20f, 4f, 20f)
                horizontalLineTo(20f)
                curveTo(21.1f, 20f, 22f, 19.1f, 22f, 18f)
                verticalLineTo(6f)
                curveTo(22f, 4.9f, 21.1f, 4f, 20f, 4f)
                close()
                moveTo(20f, 18f)
                horizontalLineTo(4f)
                verticalLineTo(8f)
                horizontalLineTo(20f)
                verticalLineTo(18f)
                close()
                // Prompt >
                moveTo(6.5f, 10.5f)
                lineTo(9.5f, 13f)
                lineTo(6.5f, 15.5f)
                lineTo(7.5f, 16.5f)
                lineTo(11.5f, 13f)
                lineTo(7.5f, 9.5f)
                close()
                // Cursor line _
                moveTo(13f, 15f)
                horizontalLineTo(17f)
                verticalLineTo(16.5f)
                horizontalLineTo(13f)
                close()
            }
        }.build()
    }

    val Math: ImageVector by lazy {
        ImageVector.Builder(
            name = "MathIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF818CF8))) {
                // Sigma symbol
                moveTo(5f, 5f)
                horizontalLineTo(19f)
                verticalLineTo(8f)
                horizontalLineTo(9.5f)
                lineTo(13.5f, 12f)
                lineTo(9.5f, 16f)
                horizontalLineTo(19f)
                verticalLineTo(19f)
                horizontalLineTo(5f)
                lineTo(11f, 12f)
                close()
            }
        }.build()
    }

    val Brain: ImageVector by lazy {
        ImageVector.Builder(
            name = "BrainIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFA78BFA))) {
                moveTo(12f, 3f)
                curveTo(9.8f, 3f, 8f, 4.8f, 8f, 7f)
                curveTo(6.3f, 7.3f, 5f, 8.8f, 5f, 10.5f)
                curveTo(5f, 11.2f, 5.2f, 11.8f, 5.6f, 12.3f)
                curveTo(4.6f, 13.2f, 4f, 14.5f, 4f, 16f)
                curveTo(4f, 18.8f, 6.2f, 21f, 9f, 21f)
                curveTo(10.1f, 21f, 11.1f, 20.6f, 11.8f, 20f)
                horizontalLineTo(12.2f)
                curveTo(12.9f, 20.6f, 13.9f, 21f, 15f, 21f)
                curveTo(17.8f, 21f, 20f, 18.8f, 20f, 16f)
                curveTo(20f, 14.5f, 19.4f, 13.2f, 18.4f, 12.3f)
                curveTo(18.8f, 11.8f, 19f, 11.2f, 19f, 10.5f)
                curveTo(19f, 8.8f, 17.7f, 7.3f, 16f, 7f)
                curveTo(16f, 4.8f, 14.2f, 3f, 12f, 3f)
                close()
            }
        }.build()
    }

    val DataChart: ImageVector by lazy {
        ImageVector.Builder(
            name = "DataChart",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF34D399))) {
                moveTo(4f, 20f)
                horizontalLineTo(20f)
                verticalLineTo(22f)
                horizontalLineTo(2f)
                verticalLineTo(4f)
                horizontalLineTo(4f)
                close()
                // Bars
                moveTo(7f, 13f)
                horizontalLineTo(9f)
                verticalLineTo(19f)
                horizontalLineTo(7f)
                close()
                moveTo(11.5f, 9f)
                horizontalLineTo(13.5f)
                verticalLineTo(19f)
                horizontalLineTo(11.5f)
                close()
                moveTo(16f, 5f)
                horizontalLineTo(18f)
                verticalLineTo(19f)
                horizontalLineTo(16f)
                close()
            }
        }.build()
    }

    val UserSilhouette: ImageVector by lazy {
        ImageVector.Builder(
            name = "UserSilhouette",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF818CF8))) {
                moveTo(12f, 12f)
                arcTo(4f, 4f, 0f, true, false, 12f, 4f)
                arcTo(4f, 4f, 0f, true, false, 12f, 12f)
                close()
                moveTo(4f, 20f)
                curveTo(4f, 16.7f, 7.6f, 14f, 12f, 14f)
                curveTo(16.4f, 14f, 20f, 16.7f, 20f, 20f)
                verticalLineTo(21f)
                horizontalLineTo(4f)
                close()
            }
        }.build()
    }

    val Function: ImageVector by lazy {
        ImageVector.Builder(
            name = "FunctionIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF67E8F9))) {
                // 'f' symbol
                moveTo(16f, 4f)
                curveTo(13.8f, 4f, 12f, 5.8f, 12f, 8f)
                verticalLineTo(10f)
                horizontalLineTo(9f)
                verticalLineTo(13f)
                horizontalLineTo(12f)
                verticalLineTo(20f)
                horizontalLineTo(15f)
                verticalLineTo(13f)
                horizontalLineTo(18f)
                verticalLineTo(10f)
                horizontalLineTo(15f)
                verticalLineTo(8f)
                curveTo(15f, 7.4f, 15.4f, 7f, 16f, 7f)
                horizontalLineTo(18f)
                verticalLineTo(4f)
                close()
            }
        }.build()
    }

    val Integral: ImageVector by lazy {
        ImageVector.Builder(
            name = "IntegralIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFBBF24))) {
                // Integral symbol ∫
                moveTo(15f, 3f)
                curveTo(12f, 3f, 11f, 5f, 11f, 7f)
                verticalLineTo(17f)
                curveTo(11f, 19f, 10f, 21f, 7f, 21f)
                horizontalLineTo(6f)
                verticalLineTo(19f)
                horizontalLineTo(7f)
                curveTo(8.5f, 19f, 9f, 18f, 9f, 17f)
                verticalLineTo(7f)
                curveTo(9f, 5f, 10.5f, 3f, 14f, 3f)
                close()
            }
        }.build()
    }
}
