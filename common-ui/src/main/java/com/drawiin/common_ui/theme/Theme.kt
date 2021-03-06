package com.drawiin.common_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PrimaryRed,
    background = PrimarySilver,
    surface = PrimarySilver,
    onPrimary = PrimaryDark,
    onBackground = PrimaryDark,
    onSurface = PrimaryDark,
)

private val LightColorPalette = lightColors(
    primary = PrimaryRed,
    background = PrimarySilver,
    surface = PrimarySilver,
    onPrimary = PrimaryDark,
    onBackground = PrimaryDark,
    onSurface = PrimaryDark,
)

@Composable
fun ComicLoverTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}