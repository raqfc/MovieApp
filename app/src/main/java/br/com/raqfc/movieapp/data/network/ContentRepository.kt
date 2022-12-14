package br.com.raqfc.movieapp.data.network

import br.com.raqfc.movieapp.data.network.httpservice.RetrofitCapsule
import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.ui.presentation.view_model.ContentFetchType
import javax.inject.Inject

class ContentRepository @Inject constructor(val retrofitCapsule: RetrofitCapsule) {
    suspend fun getContent(
        contentFetchType: ContentFetchType
    ): Result<MutableList<ContentEntity>> {
        return try {
            val items = when(contentFetchType){
                is ContentFetchType.Search ->    retrofitCapsule.searchItems(
                    contentFetchType.path,
                    contentFetchType.contentType,
                    contentFetchType.search
                )
                is ContentFetchType.Top250 -> retrofitCapsule.listItems(contentFetchType.path, contentFetchType.contentType)
            }

            Result.success(items.map {
                it.toEntity()
            }.toMutableList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}