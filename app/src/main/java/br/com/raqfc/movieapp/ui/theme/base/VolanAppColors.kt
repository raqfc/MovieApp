package br.com.raqfc.movieapp.ui.theme.base


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class VolanAppColors(
    otherColor: Color,
    isLight: Boolean
) {
    var otherColor by mutableStateOf(otherColor)
        private set
    var isLight by mutableStateOf(isLight)
        private set
    fun copy(
        otherColor: Color =  this.otherColor,
        isLight: Boolean = this.isLight
    ): VolanAppColors = VolanAppColors(
        otherColor,
        isLight
    )

    fun updateColorsFrom(other: VolanAppColors) {
        otherColor = other.otherColor
        isLight = other.isLight
    }
}