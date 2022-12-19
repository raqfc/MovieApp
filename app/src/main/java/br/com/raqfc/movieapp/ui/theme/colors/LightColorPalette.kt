package br.com.raqfc.movieapp.ui.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import br.com.raqfc.movieapp.ui.theme.base.VolanAppColors
import okhttp3.internal.wait

private val colorLightPrimary = Color(0xFFFFB400)
private val colorLightTextPrimary = Color(0xFF000000)
private val colorLightTextSecondary = Color(0xFF6C727A)
private val colorLightBackground = Color(0xFFFFFFFF)
private val colorLightError = Color(0xFFD62222)

//cores custom do sistema
val LightAppColors = VolanAppColors(
    otherColor = colorLightPrimary,
    isLight = false
)

val LightColorScheme = darkColorScheme(
    primary = Purple700,
    onPrimary = Color.White,
//    primaryContainer = Blue30,
//    onPrimaryContainer = Blue90,
//    inversePrimary = Blue40,
//    secondary = DarkBlue80,
//    onSecondary = DarkBlue20,
//    secondaryContainer = DarkBlue30,
//    onSecondaryContainer = DarkBlue90,
//    tertiary = Yellow80,
//    onTertiary = Yellow20,
//    tertiaryContainer = Yellow30,
//    onTertiaryContainer = Yellow90,
//    error = Red80,
//    onError = Red20,
//    errorContainer = Red30,
//    onErrorContainer = Red90,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
//    inverseSurface = Grey90,
//    inverseOnSurface = Grey20,
    surfaceVariant = Purple200.copy(alpha = 0.2f),
    onSurfaceVariant = Color.Black,
//    outline = BlueGrey60
)