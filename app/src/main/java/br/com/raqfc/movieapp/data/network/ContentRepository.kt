package br.com.raqfc.movieapp.data.network

import android.util.Log
import br.com.raqfc.movieapp.data.dto.ContentDTO
import br.com.raqfc.movieapp.data.dto.FullContentDTO
import br.com.raqfc.movieapp.data.network.httpservice.RetrofitCapsule
import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.domain.entities.FullContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class ContentRepository @Inject constructor(private val retrofitCapsule: RetrofitCapsule) {
    private val listMemoryCache: MutableMap<ContentType, List<ContentDTO>> = mutableMapOf()
    private val fullMemoryCache: MutableMap<String, FullContentDTO> = mutableMapOf()
    private val itemMutex = Mutex()

    suspend fun getContent(
        forceRefresh: Boolean,
        contentType: ContentType
    ): Result<MutableList<ContentEntity>> {
        return withContext(Dispatchers.IO) {
            try {
                val cachedItems = itemMutex.withLock { listMemoryCache[contentType] }
                val items = if(!forceRefresh && !cachedItems.isNullOrEmpty()) {
                    cachedItems
                } else {
                    retrofitCapsule.listItems(contentType)
                }

                itemMutex.withLock {
                    listMemoryCache[contentType] = items
                }

                Result.success(items.map {
                    it.toEntity()
                }.sortedBy { it.rank }.toMutableList())
            } catch (e: Exception) {
                Log.e("getContent", e.message ?: "")
                e.printStackTrace()
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
            Log.e("searchContent", e.message ?: "")
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun fetchMultiple(contentIds: MutableList<String>): Result<MutableList<ContentEntity>> {
        return withContext(Dispatchers.IO) {
            val contents = mutableListOf<Deferred<Result<FullContentEntity>>>()
            for (content in contentIds) {
                contents.add(async { fetchItem(content) })
            }
            Result.success(contents.awaitAll().mapNotNull {
                it.fold(onSuccess = { fc ->
                    fc.resume()
                }, onFailure = {
                    Log.e("fetchMultiple", it.message ?: "  ")
                    null
                })
            }.sortedBy { it.title }.toMutableList())
        }
    }

    suspend fun fetchItem(contentId: String): Result<FullContentEntity> {
        return try {
            itemMutex.withLock {
               if(fullMemoryCache[contentId] != null && fullMemoryCache[contentId]?.toEntity() != null)
                   return Result.success(fullMemoryCache[contentId]?.toEntity()!!)
            }
            val item = retrofitCapsule.fetchItem(contentId)

            itemMutex.withLock {
                fullMemoryCache[contentId] = item
            }

            item.toEntity()?.let { Result.success(it) } ?: throw Exception("Couldnt parse FullContentDTO to Entity")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}