package br.com.raqfc.movieapp.data.network.httpservice

data class IMDbReturnType(
    var items: MutableList<HashMap<String, Any>> = mutableListOf(),
    var errorMessage: String? = null
)