package br.com.raqfc.movieapp.domain.entities

import br.com.raqfc.movieapp.domain.enums.ContentType

data class ContentEntity (
    val id: String,
    val rank: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val crew: String,
    val rating: String,
    val ratingCount: String,
//    val type: ContentType,
    var isFavorite: Boolean = false
)

