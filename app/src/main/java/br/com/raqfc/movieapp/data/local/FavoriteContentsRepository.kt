package br.com.raqfc.movieapp.data.local

import br.com.raqfc.movieapp.data.local.cache.FavoriteContentsCacheLocal
import br.com.raqfc.movieapp.domain.entities.FavoriteContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType
import javax.inject.Inject

class FavoriteContentsRepository @Inject constructor(val cacheLocal: FavoriteContentsCacheLocal) {

    suspend fun getAllFavorites(): MutableList<String> {
        return cacheLocal.get().map { it.id }.toMutableList()
    }

    suspend fun setFavorite(id: String, isFavorite: Boolean) {
        val entry = FavoriteContentEntity(id)
        if(isFavorite) {
            cacheLocal.addOrUpdate(entry)
        } else {
            cacheLocal.delete(entry)
        }
    }

    suspend fun isFavorite(id: String): Boolean {
        return cacheLocal.getById(id) != null
    }
}