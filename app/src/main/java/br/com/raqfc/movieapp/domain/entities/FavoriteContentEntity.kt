package br.com.raqfc.movieapp.domain.entities

import br.com.raqfc.movieapp.data.local.cache.entity_cache_local.base.CacheableModel
import br.com.raqfc.movieapp.data.local.entity.RFavoriteContent
import br.com.raqfc.movieapp.domain.enums.ContentType

class FavoriteContentEntity(
    id: String,
    type: ContentType
) : BaseEntity(id), CacheableModel<RFavoriteContent, FavoriteContentEntity> {
    val type: ContentType

    init {
        this.type = type
    }

    override fun toRoomEntity(): RFavoriteContent {
        return RFavoriteContent(
            id = id,
            type = type.name
        )
    }

    override fun clone(): FavoriteContentEntity {
        return FavoriteContentEntity(
            id, type
        )
    }
}