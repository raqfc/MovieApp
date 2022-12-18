package br.com.raqfc.movieapp.common

sealed class DataResource<T>() {
    data class Success<T>(val data: T) : DataResource<T>()
    data  class Error<T>(val e: Exception) : DataResource<T>()
    class Loading<T> : DataResource<T>()
}