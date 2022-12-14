package br.com.raqfc.movieapp.data.local.cache.entity_cache_local.base

interface CacheableModel<T, M> {
    fun toRoomEntity(): T
    fun clone(): M
}