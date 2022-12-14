package br.com.raqfc.movieapp.data.local

import br.com.raqfc.movieapp.data.local.cache.FavoriteContentsCacheLocal
import br.com.raqfc.movieapp.domain.entities.FavoriteContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType
import javax.inject.Inject

class FavoriteContentsRepository @Inject constructor(val cacheLocal: FavoriteContentsCacheLocal) {

    suspend fun getAllFavorites(contentType: ContentType): MutableList<String> {
        return cacheLocal.get {
            it.type == contentType
        }.map { it.id }.toMutableList()
    }

    suspend fun setFavorite(id: String, contentType: ContentType, isFavorite: Boolean) {
        val entry = FavoriteContentEntity(id, contentType)
        if(isFavorite) {
            cacheLocal.addOrUpdate(entry)
        } else {
            cacheLocal.delete(entry)
        }
    }
}