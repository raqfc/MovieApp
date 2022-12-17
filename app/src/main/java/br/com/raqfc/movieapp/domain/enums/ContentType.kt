package br.com.raqfc.movieapp.domain.enums

enum class ContentType(val topPath: String, val searchPath: String) {
    TV("Top250TVs","SearchSeries"),
    MOVIE("Top250Movies", "SearchMovie");
    companion object {
        fun fromString(type: String): ContentType {
            return when(type) {
                TV.name -> TV
                else -> MOVIE
            }
        }
    }
}