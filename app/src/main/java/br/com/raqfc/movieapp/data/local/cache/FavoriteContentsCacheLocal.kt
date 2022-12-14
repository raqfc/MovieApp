package br.com.raqfc.movieapp.data.local.cache

import br.com.raqfc.movieapp.data.local.cache.entity_cache_local.base.BaseEntityCacheLocal
import br.com.raqfc.movieapp.data.local.dao.RFavoriteContentsDAO
import br.com.raqfc.movieapp.data.local.entity.RFavoriteContent
import br.com.raqfc.movieapp.data.local.room.AppRoomDatabase
import br.com.raqfc.movieapp.domain.entities.FavoriteContentEntity

class FavoriteContentsCacheLocal(roomInstance: AppRoomDatabase) :
    BaseEntityCacheLocal<RFavoriteContent, RFavoriteContentsDAO, FavoriteContentEntity>() {
    override val mDAO: RFavoriteContentsDAO by lazy { roomInstance.favoriteContentsDAO() }
}