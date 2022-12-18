package br.com.raqfc.movieapp.presentation.fullcontent

import br.com.raqfc.movieapp.domain.entities.FullContentEntity

sealed class FullContentUiState {
    object ShowError: FullContentUiState()
    data class UpdateToolbar(val fullContentEntity: FullContentEntity): FullContentUiState()
    object Default: FullContentUiState()
}