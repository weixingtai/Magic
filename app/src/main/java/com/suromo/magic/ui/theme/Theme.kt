package com.suromo.magic.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = Green700,
    primaryVariant = Green900,
    onPrimary = Color.White,
    secondary = Green700,
    secondaryVariant = Green900,
    onSecondary = Color.White,
    error = Green800,
    onBackground = Color.Black,

    )

private val DarkThemeColors = darkColors(
    primary = Green300,
    primaryVariant = Green700,
    onPrimary = Color.Black,
    secondary = Green300,
    onSecondary = Color.Black,
    error = Green200,
    onBackground = Color.White
)

@Composable
fun MagicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = MagicTypography,
        shapes = MagicShapes,
        content = content
    )
}