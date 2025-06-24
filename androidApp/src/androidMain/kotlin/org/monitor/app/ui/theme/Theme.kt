package org.monitor.app.ui.theme

import android.app.Activity
import android.content.res.Resources.Theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val AppColorPalette = darkColorScheme(
    primary = vib_red_orange,
    onPrimary = Color.White,
    primaryContainer = deep_orange,
    onPrimaryContainer = Color.White,
    inversePrimary = cool_blue,

    secondary = vib_green,
    onSecondary = Color.White,
    secondaryContainer = vib_green.copy(alpha = 0.2f),
    onSecondaryContainer = vib_green,

    tertiary = neon_yellow,
    onTertiary = Color.Black,
    tertiaryContainer = neon_yellow.copy(alpha = 0.2f),
    onTertiaryContainer = Color.Black,

    background = baseDark,
    onBackground = light_gray,

    surface = surfaceDark,
    onSurface = light_gray,

    surfaceVariant = dim_gray,
    onSurfaceVariant = medium_gray,

    surfaceTint = vib_red_orange,

    inverseSurface = light_gray,
    inverseOnSurface = baseDark,

    error = bright_red,
    onError = Color.White,
    errorContainer = bright_red.copy(alpha = 0.2f),
    onErrorContainer = Color.White,

    outline = dim_gray,
    outlineVariant = medium_gray,

    scrim = Color.Black.copy(alpha = 0.5f),

    surfaceBright = baseDark.copy(alpha = 0.9f),
    surfaceContainer = baseDark.copy(alpha = 0.8f),
    surfaceContainerHigh = baseDark.copy(alpha = 0.85f),
    surfaceContainerHighest = baseDark.copy(alpha = 0.9f),
    surfaceContainerLow = baseDark.copy(alpha = 0.7f),
    surfaceContainerLowest = baseDark.copy(alpha = 0.6f),
    surfaceDim = surfaceDark.copy(alpha = 0.95f)
)


@Composable
fun AppTheme(useDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit){
    val context = LocalContext.current

    val colorScheme = AppColorPalette
    val view = LocalView.current

    if(!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Type,
        shapes = Shape,
        content = content
    )
}