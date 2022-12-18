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
//        val result = service.listItems(contentType.topPath, IMDb_API_KEY).await()

        val result = Gson().fromJson(MockRetrofitData.listMovieItems, IMDbReturnType::class.java)

        if(!result.errorMessage.isNullOrBlank()) {
            throw Error(result.errorMessage)
        }


        return result.items.mapNotNull { map ->
            try {
//                ContentDTO.fromMap(map, contentType)
                Gson().fromJson(Gson().toJson(map), ContentDTO::class.java)
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
        val result = service.searchItems(contentType.searchPath, IMDb_API_KEY, search).await()

        return result.items.mapNotNull { map ->
            try {
                ContentDTO.fromMap(map, contentType)
            } catch (e: Exception) {
                Log.e("RetrofitCapsule", e.message ?: "")
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun fetchItem(itemId: String): FullContentDTO {
        val result = MockRetrofitData.fullContentItem
//        val resul = service.fetchItem("Title", IMDb_API_KEY, itemId, "Trailer,Ratings")


        return Gson().fromJson(result, FullContentDTO::class.java)
    }

}