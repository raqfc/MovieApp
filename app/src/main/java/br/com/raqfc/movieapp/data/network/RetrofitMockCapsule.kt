package br.com.raqfc.movieapp.data.network

import android.util.Log
import br.com.raqfc.movieapp.data.dto.ContentDTO
import br.com.raqfc.movieapp.data.dto.FullContentDTO
import br.com.raqfc.movieapp.data.network.httpservice.IMDbFetchReturnType
import br.com.raqfc.movieapp.data.network.httpservice.IMDbSearchReturnType
import br.com.raqfc.movieapp.domain.enums.ContentType
import com.google.gson.Gson

class RetrofitMockCapsule {
    suspend fun listItems(contentType: ContentType): List<ContentDTO> {
        val result = Gson().fromJson(MockRetrofitData.listMovieItems, IMDbFetchReturnType::class.java)

        if(!result.errorMessage.isNullOrBlank()) {
            throw Error(result.errorMessage)
        }

        return result.items.mapNotNull { map ->
            try {
                ContentDTO.fromMap(map)
            } catch (e: Exception) {
                Log.e("RetrofitCapsule", e.message ?: "")
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun searchItems(
        contentType: ContentType,
        search: String
    ): List<ContentDTO> {
        val result = Gson().fromJson(MockRetrofitData.searchMovieItems, IMDbSearchReturnType::class.java)

        if(!result.errorMessage.isNullOrBlank()) {
            throw Error(result.errorMessage)
        }

        return result.results.mapNotNull { map ->
            try {
                ContentDTO.fromMap(map)
            } catch (e: Exception) {
                Log.e("RetrofitCapsule", e.message ?: "")
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun fetchItem(itemId: String): FullContentDTO {
        val result = MockRetrofitData.fullContentItem

        return Gson().fromJson(result, FullContentDTO::class.java)
    }

}