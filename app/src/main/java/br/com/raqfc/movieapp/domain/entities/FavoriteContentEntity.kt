package br.com.raqfc.movieapp.domain.entities

import br.com.raqfc.movieapp.data.local.cache.entity_cache_local.base.CacheableModel
import br.com.raqfc.movieapp.data.local.entity.RFavoriteContent
import br.com.raqfc.movieapp.domain.enums.ContentType

class FavoriteContentEntity(
    id: String,
) : BaseEntity(id), CacheableModel<RFavoriteContent, FavoriteContentEntity> {


    override fun toRoomEntity(): RFavoriteContent {
        return RFavoriteContent(
            id = id
        )
    }

    override fun clone(): FavoriteContentEntity {
        return FavoriteContentEntity(
            id
        )
    }
}