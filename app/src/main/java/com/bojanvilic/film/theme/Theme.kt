package com.bojanvilic.film.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

//    val colors = when {
//        darkTheme -> darkThemeColors
//        else -> lightThemeColors
//    }

    MaterialTheme(
        colorScheme = lightThemeColors,
        shapes = AppShapes,
        content = content,
        typography = AppTypography
    )
}

private val lightThemeColors = lightColorScheme(
    primary = light_theme_primary,
    secondary = light_theme_secondary,
    tertiary = light_theme_tertiary,
    error = light_theme_error,
    errorContainer = light_theme_errorContainer,
    onError = light_theme_onError,
    onErrorContainer = light_theme_onErrorContainer,
    background = light_theme_background,
    onBackground = light_theme_onBackground,
    surfaceVariant = light_theme_surfaceVariant,
    surfaceTint = light_theme_surfaceTint,
    onSurfaceVariant = light_theme_onSurfaceVariant
)

private val darkThemeColors = darkColorScheme(
    primary = dark_theme_primary,
    secondary = dark_theme_secondary,
    tertiary = dark_theme_tertiary,
    error = dark_theme_error,
    errorContainer = dark_theme_errorContainer,
    onError = dark_theme_onError,
    onErrorContainer = dark_theme_onErrorContainer,
    background = dark_theme_background,
    onBackground = dark_theme_onBackground,
    surfaceTint = dark_theme_surfaceTint,
    surfaceVariant = dark_theme_surfaceVariant,
    onSurfaceVariant = dark_theme_onSurfaceVariant,
    inverseSurface = dark_theme_inverseSurface,
    inverseOnSurface = dark_theme_inverseOnSurface
)