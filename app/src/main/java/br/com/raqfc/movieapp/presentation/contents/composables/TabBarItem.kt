package br.com.raqfc.movieapp.presentation.contents.composables

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.raqfc.movieapp.domain.enums.ContentType

data class TabBarItem(
    @StringRes val titleRes: Int,
    val iconUnselected: ImageVector,
    val iconSelected: ImageVector,
    val contentType: ContentType? = null,
    val isFavorites: Boolean = false
)