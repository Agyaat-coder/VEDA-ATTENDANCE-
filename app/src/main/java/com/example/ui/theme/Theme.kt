package com.example.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = VedaBlueLight,
    onPrimary = Color.Black,
    primaryContainer = VedaBlueDark,
    onPrimaryContainer = Color.White,
    secondary = VedaSky,
    onSecondary = Color.Black,
    background = VedaNightBg,
    onBackground = Color(0xFFF1F5F9),
    surface = VedaNightCard,
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = VedaNightBorder,
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = VedaNightBorder
)

private val LightColorScheme = lightColorScheme(
    primary = VedaBluePrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDBEAFE),
    onPrimaryContainer = VedaBlueDark,
    secondary = VedaSky,
    onSecondary = Color.White,
    background = VedaBgLight,
    onBackground = VedaTextPrimary,
    surface = VedaSurfaceLight,
    onSurface = VedaTextPrimary,
    surfaceVariant = VedaSurfaceVariant,
    onSurfaceVariant = VedaTextSecondary,
    outline = VedaBorderLight
)

enum class AppThemeMode {
    SYSTEM, LIGHT, DARK
}

@Composable
fun VedaTheme(
    themeMode: AppThemeMode = AppThemeMode.SYSTEM,
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode) {
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
        AppThemeMode.LIGHT -> false
        AppThemeMode.DARK -> true
    }

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = Color.Transparent.toArgb()
                window.navigationBarColor = Color.Transparent.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
