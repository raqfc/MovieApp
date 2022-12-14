package br.com.raqfc.movieapp.data.dto

import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType

data class ContentDTO(
    val id: String,
    val rank: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val crew: String,
    val imDbRating : String,
    val imDbRatingCount : String,
    val type: ContentType
): BaseDTO<ContentEntity>() {


    companion object {
        fun fromMap(data: MutableMap<String, Any>, type: ContentType): ContentDTO? {
            return ContentDTO(
                id = data["id"] as? String ?: return null,
                rank = data["rank"] as? String  ?: return null,
                title = data["title"] as? String  ?: return null,
                fullTitle = data["fullTitle"] as? String  ?: return null,
                year = data["year"] as? String  ?: return null,
                image = data["image"] as? String  ?: return null,
                crew = data["crew"] as? String  ?: return null,
                imDbRating = data["imDbRating"] as? String  ?: return null,
                imDbRatingCount = data["imDbRatingCount"] as? String  ?: return null,
                type = type
            )
        }
    }

    override fun toEntity(): ContentEntity {
        return ContentEntity(
            id = id,
            rank = rank,
            title = title,
            fullTitle = fullTitle,
            year = year,
            image = image,
            crew = crew,
            rating = imDbRating,
            ratingCount = imDbRatingCount,
            type = type
        )
    }
}