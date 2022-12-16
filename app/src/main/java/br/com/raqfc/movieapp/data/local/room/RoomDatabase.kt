package br.com.raqfc.movieapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.raqfc.movieapp.data.local.dao.RFavoriteContentsDAO
import br.com.raqfc.movieapp.data.local.entity.RFavoriteContent

@Database(
    entities = [
        RFavoriteContent::class
    ], version = 1, exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun favoriteContentsDAO(): RFavoriteContentsDAO
}
