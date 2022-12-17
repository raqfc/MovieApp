package br.com.raqfc.movieapp.data.network

import android.util.Log
import br.com.raqfc.movieapp.data.dto.ContentDTO
import br.com.raqfc.movieapp.data.network.httpservice.RetrofitCapsule
import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContentRepository @Inject constructor(val retrofitCapsule: RetrofitCapsule) {
    private val memoryCache: MutableMap<ContentType, List<ContentDTO>> = mutableMapOf()
    private val itemMutex = Mutex()

    suspend fun getContent(
        forceRefresh: Boolean,
        contentType: ContentType
    ): Result<MutableList<ContentEntity>> {
        return withContext(Dispatchers.IO) {
            try {
                val cachedItems = itemMutex.withLock { memoryCache[contentType] }
                val items = if(!forceRefresh && !cachedItems.isNullOrEmpty()) {
                    Log.e("Getting cached", "")
                    cachedItems
                } else {
                    Log.e("Fetching data", "")
                    retrofitCapsule.listItems(contentType)
                }

                Log.e("data", items.toString())
                itemMutex.withLock {
                    memoryCache[contentType] = items
                }

                Result.success(items.map {
                    it.toEntity()
                }.sortedBy { it.rank }.toMutableList())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun searchContent(contentType: ContentType, search: String): Result<MutableList<ContentEntity>> {
        return try {
            val items = retrofitCapsule.searchItems(contentType, search)

            Result.success(items.map {
                it.toEntity()
            }.toMutableList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}