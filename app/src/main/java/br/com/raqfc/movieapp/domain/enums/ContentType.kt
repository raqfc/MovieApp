package br.com.raqfc.movieapp.domain.enums

enum class ContentType(val topPath: String, val searchPath: String) {
    Tv("Top250TVs","SearchSeries"),
    Movie("Top250Movies", "SearchMovie");
    companion object {
        fun fromString(type: String): ContentType {
            return when(type.lowercase()) {
                Tv.name.lowercase() -> Tv
                else -> Movie
            }
        }
    }
}