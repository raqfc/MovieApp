package br.com.raqfc.movieapp.data.dto

import br.com.raqfc.movieapp.domain.entities.FullContentEntity

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
    var ratings: Ratings? = null
    var wikipedia: Any? = null
    var posters: Any? = null
    var images: Any? = null
    var trailer: Trailer? = null
    var boxOffice: BoxOfficeDTO? = null
    var tagline: Any? = null
    var keywords: String? = null
    var keywordList: List<String>? = null
    var similars: List<Similar>? = null
    var tvSeriesInfo: Any? = null
    var tvEpisodeInfo: Any? = null
    var errorMessage: String? = null
    override fun toEntity(): FullContentEntity? {
        TODO("Not yet implemented")
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


class Ratings {
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

class Similar {
    var id: String? = null
    var title: String? = null
    var image: String? = null
    var imDbRating: String? = null
}

class Trailer {
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

