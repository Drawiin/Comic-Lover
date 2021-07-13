package com.example.comiclover.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryDarkColor,
    secondary = SecondaryColor,
    background = Color.Black,
    surface = Color.White,
    onPrimary = PrimaryTextColor,
    onSecondary = SecondaryTextColor,
    onBackground = PrimaryTextColor,
    onSurface = PrimaryTextColor,
)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryDarkColor,
    secondary = SecondaryColor,
    background = Color.Black,
    surface = Color.White,
    onPrimary = PrimaryTextColor,
    onSecondary = SecondaryTextColor,
    onBackground = PrimaryTextColor,
    onSurface = PrimaryTextColor,
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