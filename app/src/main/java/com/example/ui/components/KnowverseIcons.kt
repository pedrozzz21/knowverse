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

    val ChemistryFlask: ImageVector by lazy {
        ImageVector.Builder(
            name = "ChemistryFlaskIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF06B6D4))) {
                // Erlenmeyer Flask
                moveTo(9f, 2f)
                horizontalLineTo(15f)
                curveTo(15.55f, 2f, 16f, 2.45f, 16f, 3f)
                curveTo(16f, 3.55f, 15.55f, 4f, 15f, 4f)
                horizontalLineTo(14f)
                verticalLineTo(9f)
                lineTo(19.55f, 18.25f)
                curveTo(20.3f, 19.5f, 19.4f, 21f, 17.95f, 21f)
                horizontalLineTo(6.05f)
                curveTo(4.6f, 21f, 3.7f, 19.5f, 4.45f, 18.25f)
                lineTo(10f, 9f)
                verticalLineTo(4f)
                horizontalLineTo(9f)
                curveTo(8.45f, 4f, 8f, 3.55f, 8f, 3f)
                curveTo(8.45f, 2.45f, 8.45f, 2f, 9f, 2f)
                close()
                // Liquid inside flask
                moveTo(8.5f, 14f)
                lineTo(6.5f, 19f)
                horizontalLineTo(17.5f)
                lineTo(15.5f, 14f)
                horizontalLineTo(8.5f)
                close()
            }
        }.build()
    }

    val SimuladoExam: ImageVector by lazy {
        ImageVector.Builder(
            name = "SimuladoExamIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF60A5FA))) {
                moveTo(19f, 3f)
                horizontalLineTo(14.82f)
                curveTo(14.4f, 1.84f, 13.3f, 1f, 12f, 1f)
                curveTo(10.7f, 1f, 9.6f, 1.84f, 9.18f, 3f)
                horizontalLineTo(5f)
                curveTo(3.9f, 3f, 3f, 3.9f, 3f, 5f)
                verticalLineTo(19f)
                curveTo(3f, 20.1f, 3.9f, 21f, 5f, 21f)
                horizontalLineTo(19f)
                curveTo(20.1f, 21f, 21f, 20.1f, 21f, 19f)
                verticalLineTo(5f)
                curveTo(21f, 3.9f, 20.1f, 3f, 19f, 3f)
                close()
                moveTo(12f, 3f)
                curveTo(12.55f, 3f, 13f, 3.45f, 13f, 4f)
                curveTo(13f, 4.55f, 12.55f, 5f, 12f, 5f)
                curveTo(11.45f, 5f, 11f, 4.55f, 11f, 4f)
                curveTo(11f, 3.45f, 11.45f, 3f, 12f, 3f)
                close()
                moveTo(10.5f, 16.5f)
                lineTo(7f, 13f)
                lineTo(8.41f, 11.59f)
                lineTo(10.5f, 13.67f)
                lineTo(15.59f, 8.59f)
                lineTo(17f, 10f)
                lineTo(10.5f, 16.5f)
                close()
            }
        }.build()
    }

    val PhysicsAtom: ImageVector by lazy {
        ImageVector.Builder(
            name = "PhysicsAtomIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            // Nucleus
            path(fill = SolidColor(Color(0xFF06B6D4))) {
                moveTo(12f, 10f)
                curveTo(13.1f, 10f, 14f, 10.9f, 14f, 12f)
                curveTo(14f, 13.1f, 13.1f, 14f, 12f, 14f)
                curveTo(10.9f, 14f, 10f, 13.1f, 10f, 12f)
                curveTo(10f, 10.9f, 10.9f, 10f, 12f, 10f)
                close()
            }
            // Orbital rings
            path(
                stroke = SolidColor(Color(0xFF38BDF8)),
                strokeLineWidth = 1.6f
            ) {
                moveTo(4f, 12f)
                curveTo(4f, 9.2f, 7.6f, 7f, 12f, 7f)
                curveTo(16.4f, 7f, 20f, 9.2f, 20f, 12f)
                curveTo(20f, 14.8f, 16.4f, 17f, 12f, 17f)
                curveTo(7.6f, 17f, 4f, 14.8f, 4f, 12f)
                close()
                moveTo(12f, 4f)
                curveTo(14.8f, 4f, 17f, 7.6f, 17f, 12f)
                curveTo(17f, 16.4f, 14.8f, 20f, 12f, 20f)
                curveTo(9.2f, 20f, 7f, 16.4f, 7f, 12f)
                curveTo(7f, 7.6f, 9.2f, 4f, 12f, 4f)
                close()
            }
        }.build()
    }

    val BiologyLeaf: ImageVector by lazy {
        ImageVector.Builder(
            name = "BiologyLeafIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF14B8A6))) {
                moveTo(17f, 8f)
                curveTo(8f, 10f, 5.9f, 16.17f, 3.82f, 21.34f)
                lineTo(5.71f, 22f)
                lineTo(6.66f, 19.7f)
                curveTo(7.14f, 19.87f, 7.64f, 20f, 8f, 20f)
                curveTo(19f, 20f, 22f, 3f, 22f, 3f)
                curveTo(21f, 5f, 14f, 5.25f, 9f, 6.25f)
                curveTo(4f, 7.25f, 2f, 11.5f, 2f, 13.5f)
                curveTo(2f, 15.5f, 3.75f, 17.25f, 3.75f, 17.25f)
                curveTo(7f, 8f, 17f, 8f, 17f, 8f)
                close()
            }
        }.build()
    }

    val GeographyGlobe: ImageVector by lazy {
        ImageVector.Builder(
            name = "GeographyGlobeIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFF59E0B))) {
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                curveTo(2f, 17.52f, 6.48f, 22f, 12f, 22f)
                curveTo(17.52f, 22f, 22f, 17.52f, 22f, 12f)
                curveTo(22f, 6.48f, 17.52f, 2f, 12f, 2f)
                close()
                moveTo(11f, 19.93f)
                curveTo(7.05f, 19.44f, 4f, 16.08f, 4f, 12f)
                curveTo(4f, 11.38f, 4.08f, 10.79f, 4.21f, 10.21f)
                lineTo(9f, 15f)
                verticalLineTo(16f)
                curveTo(9f, 17.1f, 9.9f, 18f, 11f, 18f)
                verticalLineTo(19.93f)
                close()
                moveTo(17.9f, 17.39f)
                curveTo(17.64f, 16.58f, 16.9f, 16f, 16f, 16f)
                horizontalLineTo(15f)
                verticalLineTo(13f)
                curveTo(15f, 12.45f, 14.55f, 12f, 14f, 12f)
                horizontalLineTo(8f)
                verticalLineTo(10f)
                horizontalLineTo(10f)
                curveTo(10.55f, 10f, 11f, 9.55f, 11f, 9f)
                verticalLineTo(7f)
                horizontalLineTo(13f)
                curveTo(14.1f, 7f, 15f, 6.1f, 15f, 5f)
                verticalLineTo(4.59f)
                curveTo(17.93f, 5.78f, 20f, 8.65f, 20f, 12f)
                curveTo(20f, 14.08f, 19.2f, 15.97f, 17.9f, 17.39f)
                close()
            }
        }.build()
    }

    val HistoryColumns: ImageVector by lazy {
        ImageVector.Builder(
            name = "HistoryColumnsIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFBBF24))) {
                moveTo(12f, 2f)
                lineTo(2f, 7f)
                verticalLineTo(9f)
                horizontalLineTo(22f)
                verticalLineTo(7f)
                lineTo(12f, 2f)
                close()
                moveTo(4f, 11f)
                verticalLineTo(18f)
                horizontalLineTo(7f)
                verticalLineTo(11f)
                horizontalLineTo(4f)
                close()
                moveTo(10.5f, 11f)
                verticalLineTo(18f)
                horizontalLineTo(13.5f)
                verticalLineTo(11f)
                horizontalLineTo(10.5f)
                close()
                moveTo(17f, 11f)
                verticalLineTo(18f)
                horizontalLineTo(20f)
                verticalLineTo(11f)
                horizontalLineTo(17f)
                close()
                moveTo(2f, 20f)
                verticalLineTo(22f)
                horizontalLineTo(22f)
                verticalLineTo(20f)
                horizontalLineTo(2f)
                close()
            }
        }.build()
    }

    val LanguagesBook: ImageVector by lazy {
        ImageVector.Builder(
            name = "LanguagesBookIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF818CF8))) {
                moveTo(21f, 5f)
                curveTo(19.89f, 4.65f, 18.67f, 4.5f, 17.5f, 4.5f)
                curveTo(15.55f, 4.5f, 13.45f, 4.9f, 12f, 6f)
                curveTo(10.55f, 4.9f, 8.45f, 4.5f, 6.5f, 4.5f)
                curveTo(4.55f, 4.5f, 2.45f, 4.9f, 1f, 6f)
                verticalLineTo(20.65f)
                curveTo(1f, 20.9f, 1.25f, 21.15f, 1.5f, 21.15f)
                curveTo(1.6f, 21.15f, 1.65f, 21.1f, 1.75f, 21.1f)
                curveTo(3.1f, 20.45f, 5.05f, 20f, 6.5f, 20f)
                curveTo(8.45f, 20f, 10.55f, 20.4f, 12f, 21.5f)
                curveTo(13.35f, 20.65f, 15.8f, 20f, 17.5f, 20f)
                curveTo(19.15f, 20f, 20.85f, 20.3f, 22.25f, 21.05f)
                curveTo(22.35f, 21.1f, 22.4f, 21.1f, 22.5f, 21.1f)
                curveTo(22.75f, 21.1f, 23f, 20.85f, 23f, 20.6f)
                verticalLineTo(6f)
                curveTo(22.4f, 5.55f, 21.75f, 5.25f, 21f, 5f)
                close()
                moveTo(21f, 18.5f)
                curveTo(19.9f, 18.15f, 18.7f, 18f, 17.5f, 18f)
                curveTo(15.8f, 18f, 13.35f, 18.65f, 12f, 19.5f)
                verticalLineTo(8f)
                curveTo(13.35f, 7.15f, 15.8f, 6.5f, 17.5f, 6.5f)
                curveTo(18.7f, 6.5f, 19.9f, 6.65f, 21f, 7f)
                verticalLineTo(18.5f)
                close()
            }
        }.build()
    }

    val TargetGoal: ImageVector by lazy {
        ImageVector.Builder(
            name = "TargetGoalIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF10B981))) {
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                curveTo(2f, 17.52f, 6.48f, 22f, 12f, 22f)
                curveTo(17.52f, 22f, 22f, 17.52f, 22f, 12f)
                curveTo(22f, 6.48f, 17.52f, 2f, 12f, 2f)
                close()
                moveTo(12f, 20f)
                curveTo(7.58f, 20f, 4f, 16.42f, 4f, 12f)
                curveTo(4f, 7.58f, 7.58f, 4f, 12f, 4f)
                curveTo(16.42f, 4f, 20f, 7.58f, 20f, 12f)
                curveTo(20f, 16.42f, 16.42f, 20f, 12f, 20f)
                close()
                moveTo(12f, 6f)
                curveTo(8.69f, 6f, 6f, 8.69f, 6f, 12f)
                curveTo(6f, 15.31f, 8.69f, 18f, 12f, 18f)
                curveTo(15.31f, 18f, 18f, 15.31f, 18f, 12f)
                curveTo(18f, 8.69f, 15.31f, 6f, 12f, 6f)
                close()
                moveTo(12f, 16f)
                curveTo(9.79f, 16f, 8f, 14.21f, 8f, 12f)
                curveTo(8f, 9.79f, 9.79f, 8f, 12f, 8f)
                curveTo(14.21f, 8f, 16f, 9.79f, 16f, 12f)
                curveTo(16f, 14.21f, 14.21f, 16f, 12f, 16f)
                close()
                moveTo(12f, 10f)
                curveTo(10.9f, 10f, 10f, 10.9f, 10f, 12f)
                curveTo(10f, 13.1f, 10.9f, 14f, 12f, 14f)
                curveTo(13.1f, 14f, 14f, 13.1f, 14f, 12f)
                curveTo(14f, 10.9f, 13.1f, 10f, 12f, 10f)
                close()
            }
        }.build()
    }

    val AutoScale: ImageVector by lazy {
        ImageVector.Builder(
            name = "AutoScaleIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF38BDF8))) {
                moveTo(15f, 3f)
                lineTo(17.3f, 5.3f)
                lineTo(14.41f, 8.17f)
                lineTo(15.83f, 9.59f)
                lineTo(18.7f, 6.7f)
                lineTo(21f, 9f)
                verticalLineTo(3f)
                horizontalLineTo(15f)
                close()
                moveTo(3f, 9f)
                lineTo(5.3f, 6.7f)
                lineTo(8.17f, 9.59f)
                lineTo(9.59f, 8.17f)
                lineTo(6.7f, 5.3f)
                lineTo(9f, 3f)
                horizontalLineTo(3f)
                verticalLineTo(9f)
                close()
                moveTo(9f, 21f)
                lineTo(6.7f, 18.7f)
                lineTo(9.59f, 15.83f)
                lineTo(8.17f, 14.41f)
                lineTo(5.3f, 17.3f)
                lineTo(3f, 15f)
                verticalLineTo(21f)
                horizontalLineTo(9f)
                close()
                moveTo(21f, 15f)
                lineTo(18.7f, 17.3f)
                lineTo(15.83f, 14.41f)
                lineTo(14.41f, 15.83f)
                lineTo(17.3f, 18.7f)
                lineTo(15f, 21f)
                horizontalLineTo(21f)
                verticalLineTo(15f)
                close()
            }
        }.build()
    }
}

