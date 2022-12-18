package br.com.raqfc.movieapp.data.dto

import br.com.raqfc.movieapp.domain.entities.ContentResumeEntity
import br.com.raqfc.movieapp.domain.entities.FullContentEntity
import br.com.raqfc.movieapp.domain.entities.RatingsEntity
import br.com.raqfc.movieapp.domain.entities.TrailerEntity
import br.com.raqfc.movieapp.domain.enums.ContentType

class FullContentDTO: BaseDTO<FullContentEntity>() {
    var id: String? = null
    var title: String? = null
    var originalTitle: String? = null
    var fullTitle: String? = null
    var type: String? = null
    var year: String? = null
    var image: String? = null
    var releaseDate: String? = null
    var runtimeMins: String? = null
    var runtimeStr: String? = null
    var plot: String? = null
    var plotLocal: String? = null
    var plotLocalIsRtl = false
    var awards: String? = null
    var directors: String? = null
    var directorList: List<NameAndIdDTO>? = null
    var writers: String? = null
    var writerList: List<NameAndIdDTO>? = null
    var stars: String? = null
    var starList: List<NameAndIdDTO>? = null
    var actorList: List<ActorListDTO>? = null
    var fullCast: Any? = null
    var genres: String? = null
    var genreList: List<NameAndKeyDTO>? = null
    var companies: String? = null
    var companyList: List<NameAndIdDTO>? = null
    var countries: String? = null
    var countryList: List<NameAndIdDTO>? = null
    var languages: String? = null
    var languageList: List<NameAndKeyDTO>? = null
    var contentRating: String? = null
    var imDbRating: String? = null
    var imDbRatingVotes: String? = null
    var metaCriticRating: String? = null
    var ratings: RatingsDTO? = null
    var wikipedia: Any? = null
    var posters: Any? = null
    var images: Any? = null
    var trailer: TrailerDTO? = null
    var boxOffice: BoxOfficeDTO? = null
    var tagline: Any? = null
    var keywords: String? = null
    var keywordList: List<String>? = null
    var similars: List<ContentResumeDTO>? = null
    var tvSeriesInfo: Any? = null
    var tvEpisodeInfo: Any? = null
    var errorMessage: String? = null
    override fun toEntity(): FullContentEntity? {
        return FullContentEntity(
            id = this.id ?: return null,
            title = title ?: return null,
            fullTitle = fullTitle ?: return null,
            type = type?.let { ContentType.fromString(it) } ?: return null,
            year = year ?: return null,
            image = image ?: return null,
            releaseDate = releaseDate ?: return null,
            duration = runtimeStr ?: return null,
            plot = plot ?: return null,
            plotLocal = plotLocal,
            awards = awards,
            directors = directors ?: return null,
            writers = writers  ?: return null,
            stars = stars ?: return null,
            genres = genres ?: return null,
            contentRating = contentRating ?: return null,
            imDbRating = imDbRating ?: return null,
            imDbRatingVotes = imDbRatingVotes ?: return null,
            metaCriticRating = "",
            ratings = ratings?.let {
                RatingsEntity(
                    imDbId = it.imDbId ?: return@let null,
                    title = it.title ?: return@let null,
                    fullTitle = it.fullTitle ?: return@let null,
                    type = it.type?.let { t -> ContentType.fromString(t) } ?: return null,
                    year = it.year ?: return@let null,
                    imDb = it.imDb ?: return@let null,
                    metacritic = it.metacritic ?: return@let null,
                    theMovieDb = it.theMovieDb ?: return@let null,
                    rottenTomatoes = it.rottenTomatoes ?: return@let null,
                    filmAffinity = it.filmAffinity ?: return@let null,
                    errorMessage = it.errorMessage,
                )
            },
            trailer = trailer?.let {
                TrailerEntity(
                    imDbId = it.imDbId ?: return@let null,
                    title = it.title ?: return@let null,
                    fullTitle = it.fullTitle ?: return@let null,
                    type = it.type?.let { t -> ContentType.fromString(t) } ?: return null,
                    year = it.year ?: return@let null,
                    videoId = it.videoId ?: return@let null,
                    videoTitle = it.videoTitle ?: return@let null,
                    videoDescription = it.videoDescription ?: return@let null,
                    thumbnailUrl = it.thumbnailUrl ?: return@let null,
                    uploadDate = it.uploadDate ?: return@let null,
                    link = it.link ?: return@let null,
                    linkEmbed = it.linkEmbed ?: return@let null,
                    errorMessage = it.errorMessage,
                )
            },
            keywords = keywords,
            similars = similars?.mapNotNull {
                ContentResumeEntity(
                    id = it.id ?: return@mapNotNull null,
                    title = it.title ?: return@mapNotNull null,
                    image = it.image ?: return@mapNotNull null,
                    imDbRating = it.imDbRating ?: return@mapNotNull null,
                )
            },
        )
    }
}

class ActorListDTO {
    var id: String? = null
    var image: String? = null
    var name: String? = null
    var asCharacter: String? = null
}

class BoxOfficeDTO {
    var budget: String? = null
    var openingWeekendUSA: String? = null
    var grossUSA: String? = null
    var cumulativeWorldwideGross: String? = null
}

class NameAndIdDTO {
    var id: String? = null
    var name: String? = null
}

class NameAndKeyDTO {
    var key: String? = null
    var value: String? = null
}


class RatingsDTO {
    var imDbId: String? = null
    var title: String? = null
    var fullTitle: String? = null
    var type: String? = null
    var year: String? = null
    var imDb: String? = null
    var metacritic: String? = null
    var theMovieDb: String? = null
    var rottenTomatoes: String? = null
    var filmAffinity: String? = null
    var errorMessage: String? = null
}

class ContentResumeDTO {
    var id: String? = null
    var title: String? = null
    var image: String? = null
    var imDbRating: String? = null
}

class TrailerDTO {
    var imDbId: String? = null
    var title: String? = null
    var fullTitle: String? = null
    var type: String? = null
    var year: String? = null
    var videoId: String? = null
    var videoTitle: String? = null
    var videoDescription: String? = null
    var thumbnailUrl: String? = null
    var uploadDate: String? = null
    var link: String? = null
    var linkEmbed: String? = null
    var errorMessage: String? = null
}

