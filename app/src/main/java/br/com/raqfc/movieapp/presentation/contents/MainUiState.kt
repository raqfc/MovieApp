package br.com.raqfc.movieapp.presentation.contents

import br.com.raqfc.movieapp.domain.entities.ContentEntity

sealed class MainUiState {
    object ShowError: MainUiState()
    data class UpdateItem(val item: ContentEntity): MainUiState()
    object Default: MainUiState()
}