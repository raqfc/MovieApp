package br.com.raqfc.movieapp.domain.entities

import br.com.raqfc.movieapp.domain.enums.ContentType

data class FullContentEntity(
    var id: String,
    var title: String,
    var fullTitle: String,
    var type: ContentType,
    var year: String,
    var image: String,
    var releaseDate: String,
    var duration: String,
    var plot: String,
    var plotLocal: String?,
    var awards: String?,
    var directors: String,
    var writers: String,
    var stars: String,
    var genres: String,
    var contentRating: String,
    var imDbRating: String,
    var imDbRatingVotes: String,
    var metaCriticRating: String,
    var ratings: RatingsEntity?,
    var trailer: TrailerEntity?,
    var keywords: String?,
    var similars: List<ContentResumeEntity>?,
    var isFavorite: Boolean = false
) {
    fun resume(): ContentEntity {
        return ContentEntity(
            id = id,
            rank = "0",
            title = title,
            fullTitle = fullTitle,
            year = year,
            image = image,
            crew = "$directors, $stars",
            rating = imDbRating,
            ratingCount = imDbRatingVotes,
            isFavorite = isFavorite,
        )
    }
}

data class ContentResumeEntity(
    var id: String,
    var title: String,
    var image: String,
    var imDbRating: String,
)

data class RatingsEntity(
    var imDbId: String,
    var title: String,
    var fullTitle: String,
    var type: ContentType,
    var year: String,
    var imDb: String,
    var metacritic: String,
    var theMovieDb: String,
    var rottenTomatoes: String,
    var filmAffinity: String,
    var errorMessage: String?,
)

data class TrailerEntity(
    var imDbId: String,
    var title: String,
    var fullTitle: String,
    var type: ContentType,
    var year: String,
    var videoId: String,
    var videoTitle: String,
    var videoDescription: String,
    var thumbnailUrl: String,
    var uploadDate: String,
    var link: String,
    var linkEmbed: String?,
    var errorMessage: String?,
)