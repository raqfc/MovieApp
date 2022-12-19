package br.com.raqfc.movieapp.data.dto

import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType

data class ContentDTO(
    val id: String,
    val rank: String?,
    val title: String,
    val fullTitle: String?,
    val year: String?,
    val image: String,
    val crew: String?,
    val imDbRating : String?,
    val imDbRatingCount : String?,
): BaseDTO<ContentEntity>() {
    companion object {
        fun fromMap(data: MutableMap<String, Any>): ContentDTO? {
            return ContentDTO(
                id = data["id"] as? String ?: return null,
                rank = data["rank"] as? String,
                title = data["title"] as? String  ?: return null,
                fullTitle = data["fullTitle"] as? String,
                year = data["year"] as? String,
                image = data["image"] as? String  ?: return null,
                crew = data["crew"] as? String,
                imDbRating = data["imDbRating"] as? String,
                imDbRatingCount = data["imDbRatingCount"] as? String,
            )
        }
    }

    override fun toEntity(): ContentEntity {
        return ContentEntity(
            id = id,
            title = title,
            image = image,
            rank = rank
        )
    }
}