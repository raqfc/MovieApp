package br.com.raqfc.movieapp.domain.entities

import br.com.raqfc.movieapp.domain.enums.ContentType

data class ContentEntity (
    val id: String,
    val title: String,
    val rank: String?,
    val image: String,
    var isFavorite: Boolean = false,
)

