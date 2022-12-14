package br.com.raqfc.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.raqfc.movieapp.data.local.entity.RFavoriteContent
import br.com.raqfc.movieapp.data.local.room.RoomTables

@Dao
abstract class RFavoriteContentsDAO : BaseDAO<RFavoriteContent>(RoomTables.FAVORITE_CONTENTS) {
    @Query("SELECT * FROM favoriteContents WHERE id = :id")
    abstract suspend fun getById(id: String): RFavoriteContent
}
