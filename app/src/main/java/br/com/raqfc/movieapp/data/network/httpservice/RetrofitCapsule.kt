package br.com.raqfc.movieapp.data.network.httpservice

import android.util.Log
import br.com.raqfc.movieapp.BuildConfig.IMDb_API_KEY
import br.com.raqfc.movieapp.data.dto.ContentDTO
import br.com.raqfc.movieapp.data.dto.FullContentDTO
import br.com.raqfc.movieapp.data.network.MockRetrofitData
import br.com.raqfc.movieapp.domain.enums.ContentType
import com.google.gson.Gson
import retrofit2.await
import javax.inject.Inject

class RetrofitCapsule @Inject constructor(val service: RetrofitService) {
    suspend fun listItems(contentType: ContentType): List<ContentDTO> {
        val result = service.listItems(contentType.topPath, IMDb_API_KEY).await()

        if(!result.errorMessage.isNullOrBlank()) {
            throw Exception(result.errorMessage)
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
        val result = service.searchItems(contentType.searchPath, IMDb_API_KEY, search.trim().replace("\n","")).await()

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
        return service.fetchItem("Title", IMDb_API_KEY, itemId, "Trailer,Ratings").await()
    }

}