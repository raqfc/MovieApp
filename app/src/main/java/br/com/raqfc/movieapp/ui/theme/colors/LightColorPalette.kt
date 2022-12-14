package br.com.justworks.volan2.ui.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import br.com.justworks.volan2.ui.theme.base.VolanAppColors

//private val LightColorPalette = lightColors(
//    primary = Purple500,
//    primaryVariant = Purple700,
//    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
//)


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
//    error = Red80,
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