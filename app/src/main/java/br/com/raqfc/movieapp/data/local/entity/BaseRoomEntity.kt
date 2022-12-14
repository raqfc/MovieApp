package br.com.raqfc.movieapp.data.local.entity

abstract class BaseRoomEntity<T> {
    companion object{
        fun <T>List<BaseRoomEntity<T>>.toModel(): List<T> {
            return this.map { it.toModel() }
        }
    }
    abstract fun toModel(): T
}