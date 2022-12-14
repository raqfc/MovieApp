package br.com.raqfc.movieapp.common

sealed class ListUpdateAction<T> {
    data class OnInit<T>(val data: List<T>, val isReset: Boolean = false): ListUpdateAction<T>()
    data class OnAdded<T>(val data: T): ListUpdateAction<T>()
    data class OnRemoved<T>(val data: T): ListUpdateAction<T>()
    data class OnModified<T>(val data: T): ListUpdateAction<T>()
}