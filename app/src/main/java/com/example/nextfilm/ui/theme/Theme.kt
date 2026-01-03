package com.example.nextfilm.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PurplePrimary,
    secondary = PurpleSecondary,
    background = DarkBackground,
    surface = DarkSurface,

    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,

    error = ErrorRed
)

@Composable
fun NextFilmTheme(
content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}