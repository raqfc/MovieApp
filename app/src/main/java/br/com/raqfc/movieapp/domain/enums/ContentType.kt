package br.com.raqfc.movieapp.domain.enums

enum class ContentType(val topPath: String, val searchPath: String) {
    TV("TVs","Series"),
    MOVIE("Movies", "Movie");

    companion object {
        fun fromString(type: String): ContentType {
            return when(type) {
                TV.name -> TV
                else -> MOVIE
            }
        }
    }
}