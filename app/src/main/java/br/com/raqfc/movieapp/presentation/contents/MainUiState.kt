package br.com.raqfc.movieapp.presentation.contents

import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.domain.entities.ContentEntity

sealed class MainUiState {
    data class ShowError(val messageRes: Int = R.string.error_information_content): MainUiState()
    data class UpdateItem(val item: ContentEntity): MainUiState()
    object Default: MainUiState()
}