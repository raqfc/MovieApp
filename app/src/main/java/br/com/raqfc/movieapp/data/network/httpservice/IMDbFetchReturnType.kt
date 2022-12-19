package br.com.raqfc.movieapp.data.network.httpservice

data class IMDbFetchReturnType(
    var items: MutableList<HashMap<String, Any>> = mutableListOf(),
    var errorMessage: String? = null
)

data class IMDbSearchReturnType(
    var results: MutableList<HashMap<String, Any>> = mutableListOf(),
    var errorMessage: String? = null
)