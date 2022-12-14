package br.com.justworks.volan2.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import br.com.justworks.volan2.ui.theme.base.VolanAppColors
import br.com.raqfc.movieapp.ui.theme.LocalColors
import br.com.raqfc.movieapp.ui.theme.base.AppDimensions
import br.com.raqfc.movieapp.ui.theme.base.LocalDimensions

object AppTheme {
    val colors: VolanAppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}