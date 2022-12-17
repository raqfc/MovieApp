package br.com.raqfc.movieapp.common

sealed class ListResource<T>() {
    data class Success<T>(val data: List<T>) : ListResource<T>()
    data  class Error<T>(val e: Exception) : ListResource<T>()
    class Loading<T> : ListResource<T>()
}