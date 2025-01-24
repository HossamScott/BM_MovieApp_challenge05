package com.hossam.bm_movieapp.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



private val DarkColorPalette = darkColors(
    primary = Color(0xFF121212),
    primaryVariant = Color(0xFF1E1E2C),
    secondary = Color(0xFF03DAC5),
    background = Color(0xFF121212),
    surface = Color(0xFF232323),
    onSecondary = Color(0xFF000000)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFF5F5F5),
    primaryVariant = Color(0xFFE3E8F0),
    secondary = Color(0xFF1E88E5),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFAFAFA),
    onSecondary = Color(0xFFFFFFFF)
)


@Composable
fun BMMovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
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
