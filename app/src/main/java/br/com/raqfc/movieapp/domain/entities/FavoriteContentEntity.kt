package br.com.raqfc.movieapp.domain.entities

import br.com.raqfc.movieapp.data.local.cache.entity_cache_local.base.CacheableModel
import br.com.raqfc.movieapp.data.local.entity.RFavoriteContent
import br.com.raqfc.movieapp.domain.enums.ContentType

class FavoriteContentEntity(
    id: String,
    timestamp: Long
) : BaseEntity(id), CacheableModel<RFavoriteContent, FavoriteContentEntity> {
    val timestamp: Long

     init {
         this.timestamp = timestamp
     }

    override fun toRoomEntity(): RFavoriteContent {
        return RFavoriteContent(
            id = id,
            timestamp= timestamp
        )
    }

    override fun clone(): FavoriteContentEntity {
        return FavoriteContentEntity(
            id,
            timestamp
        )
    }
}