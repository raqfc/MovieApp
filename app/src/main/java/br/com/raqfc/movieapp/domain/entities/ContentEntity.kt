package br.com.raqfc.movieapp.domain.entities

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

