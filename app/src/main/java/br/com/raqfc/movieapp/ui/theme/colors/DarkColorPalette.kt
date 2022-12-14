package br.com.justworks.volan2.ui.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import br.com.justworks.volan2.ui.theme.base.VolanAppColors

//private val DarkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200
//)

private val colorDarkPrimary = Color(0xFF0037FF)
private val colorDarkTextPrimary = Color(0xFFFAFAFA)
private val colorDarkTextSecondary = Color(0xFF6C727A)
private val colorDarkBackground = Color(0xFF090A0A)
private val colorDarkError = Color.Red//Color(0xFFD62222)

//cores custom do sistema
val DarkAppColors = VolanAppColors(
    otherColor = colorDarkPrimary,
    isLight = false
)

val DarkColorScheme = darkColorScheme(
    primary = Purple200,
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
    error = Color.Red,
//    onError = Red20,
//    errorContainer = Red30,
//    onErrorContainer = Red90,
//    background = Grey10,
//    onBackground = Grey90,
//    surface = Grey10,
//    onSurface = Grey80,
//    inverseSurface = Grey90,
//    inverseOnSurface = Grey20,
//    surfaceVariant = BlueGrey30,
//    onSurfaceVariant = BlueGrey80,
//    outline = BlueGrey60
)
