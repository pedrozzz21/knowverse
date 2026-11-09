package com.example.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

// SaaS Minimalist Dark Color Scheme (Linear / Vercel style)
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryViolet,
    onPrimary = TextPrimary,
    primaryContainer = SurfaceCard,
    onPrimaryContainer = TextPrimary,
    secondary = PrimaryVioletLight,
    onSecondary = DeepNavy,
    secondaryContainer = SurfaceElevated,
    onSecondaryContainer = TextSecondary,
    tertiary = GoldXp,
    onTertiary = DeepNavy,
    background = DeepNavy,
    onBackground = TextPrimary,
    surface = ObsidianSurface,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceElevated,
    onSurfaceVariant = TextSecondary,
    outline = BorderSubtle,
    outlineVariant = BorderGlow,
    error = ErrorRose,
    onError = TextPrimary,
    errorContainer = ErrorRoseContainer,
    onErrorContainer = TextPrimary
)

// High-Contrast Accessible Light Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = PrimaryViolet,
    onPrimary = LightSurface,
    primaryContainer = LightSurfaceElevated,
    onPrimaryContainer = LightTextPrimary,
    secondary = AccentCyanDark,
    onSecondary = LightSurface,
    secondaryContainer = LightSurfaceElevated,
    onSecondaryContainer = LightTextSecondary,
    tertiary = GoldXp,
    onTertiary = LightTextPrimary,
    background = LightCanvasBg,
    onBackground = LightTextPrimary,
    surface = LightSurface,
    onSurface = LightTextPrimary,
    surfaceVariant = LightSurfaceElevated,
    onSurfaceVariant = LightTextSecondary,
    outline = LightBorderSubtle,
    outlineVariant = BorderSubtle,
    error = ErrorRose,
    onError = LightSurface,
    errorContainer = ErrorRoseContainer,
    onErrorContainer = TextPrimary
)

// Sharp, structured corners for hierarchy and visual clarity
private val CleanSaaSShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(6.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(10.dp),
    extraLarge = RoundedCornerShape(12.dp)
)

@Composable
fun KnowverseTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val statusBarBg = if (darkTheme) DeepNavy else LightCanvasBg
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = statusBarBg.toArgb()
                window.navigationBarColor = statusBarBg.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = CleanSaaSShapes,
        content = content
    )
}
