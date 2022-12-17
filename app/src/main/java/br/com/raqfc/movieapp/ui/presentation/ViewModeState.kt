package br.com.raqfc.movieapp.ui.presentation

import br.com.raqfc.movieapp.domain.enums.ContentType

data class ViewModeState(val contentType: ContentType, val fetchMode: ContentFetchMode, val onlyFavorites: Boolean)

enum class ContentFetchMode {
    TOP250,
    SEARCH
}