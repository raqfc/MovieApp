package br.com.raqfc.movieapp.presentation.contents.composables

import br.com.raqfc.movieapp.domain.enums.ContentType

data class ViewModeState(val contentType: ContentType, val fetchMode: ContentFetchMode)

enum class ContentFetchMode {
    TOP250,
    FAVORITES,
    SEARCH;

    companion object {
        fun fromString(mode: String): ContentFetchMode {
            return when(mode) {
                FAVORITES.name -> FAVORITES
                SEARCH.name -> SEARCH
                else -> TOP250
            }
        }
    }
}