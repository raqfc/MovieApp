package br.com.raqfc.movieapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import br.com.justworks.volan2.ui.theme.Shapes
import br.com.justworks.volan2.ui.theme.Typography
import br.com.justworks.volan2.ui.theme.base.VolanAppColors
import br.com.justworks.volan2.ui.theme.colors.DarkColorScheme
import br.com.justworks.volan2.ui.theme.colors.DarkAppColors
import br.com.justworks.volan2.ui.theme.colors.LightColorScheme
import br.com.justworks.volan2.ui.theme.colors.LightAppColors

@Composable
fun VolanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme: ColorScheme
    val colors: VolanAppColors
    if (darkTheme) {
        colorScheme = DarkColorScheme
        colors = DarkAppColors
    } else {
        colorScheme = LightColorScheme
        colors = LightAppColors
    }

    CompositionLocalProvider(
        LocalColors provides colors,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }

}

val LocalColors = staticCompositionLocalOf<VolanAppColors> {
    error("No VolanAppColors provided")
}