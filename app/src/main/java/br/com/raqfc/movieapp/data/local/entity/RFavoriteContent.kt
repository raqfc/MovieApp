package br.com.raqfc.movieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.raqfc.movieapp.data.local.room.RoomTables
import br.com.raqfc.movieapp.domain.entities.FavoriteContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType

@Entity(tableName = RoomTables.FAVORITE_CONTENTS)
data class RFavoriteContent(
    @PrimaryKey val id: String,
    val type: String
) : BaseRoomEntity<FavoriteContentEntity>() {
    override fun toModel(): FavoriteContentEntity {
        return FavoriteContentEntity(
            id = id,
            type = ContentType.fromString(type)
        )
    }
}




