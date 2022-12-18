package br.com.raqfc.movieapp.common.presentation

import androidx.annotation.StringRes
import br.com.raqfc.movieapp.R

sealed class BaseUiEvent {
    data class ShowProgressIndicator(@StringRes val content: Int = R.string.default_loading): BaseUiEvent()
//    object HideProgressIndicator: BaseUiEvent()
    data class ShowError(val error: Exception, val dismissible: Boolean = true, val onClick: () -> Unit = {}, val onDismissed: () -> Unit = {}): BaseUiEvent()
    data class ShowDialog(val title: Int, val content: Int, val dismissible: Boolean = true, val onClick: () -> Unit = {}, val onDismissed: () -> Unit = {}): BaseUiEvent()
}