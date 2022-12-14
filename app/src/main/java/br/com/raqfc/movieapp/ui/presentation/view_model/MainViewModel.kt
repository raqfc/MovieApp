package br.com.raqfc.movieapp.ui.presentation.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raqfc.movieapp.data.local.FavoriteContentsRepository
import br.com.raqfc.movieapp.data.network.ContentRepository
import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val contentRepository: ContentRepository,
    val favoritesRepository: FavoriteContentsRepository
) : ViewModel() {
    private var contents: MutableList<ContentEntity> = mutableListOf()
    var contentFetchType: ContentFetchType = ContentFetchType.Top250(ContentType.MOVIE)

    fun getContent(
        forceRefresh: Boolean,
        contentFetchType: ContentFetchType
    ){ //: Result<MutableList<ContentEntity>>
        viewModelScope.launch {
            try {
                val favorites = favoritesRepository.getAllFavorites(contentFetchType.contentType)
                val data =
                    if (!forceRefresh && contentFetchType == this@MainViewModel.contentFetchType && contents.isNotEmpty()) {
                        contents
                    } else {
                        contentRepository.getContent(contentFetchType).fold(onSuccess = {
                            it
                        }, onFailure = {
                            return@launch //Result.failure(it)
                        })
                    }

                data.forEach {
                    it.isFavorite = favorites.contains(it.id)
                }
                contents = data
                this@MainViewModel.contentFetchType = contentFetchType

                Result.success(contents)
                Log.e("MainViewModel -> getContent success:", contents.toString())
            } catch (e: Exception) {
                Log.e("MainViewModel -> getContent error:", e.message ?: "")
                e.printStackTrace()
//                Result.failure(e)
            }
        }

    }

    suspend fun toggleFavorite(
        id: String,
        contentType: ContentType,
        isFavorite: Boolean
    ): Result<Boolean> {
        favoritesRepository.setFavorite(id, contentType, isFavorite)
        return Result.success(true)
    }
}

sealed class ContentFetchType(val path: String, val contentType: ContentType) {
    data class Top250(val type: ContentType) : ContentFetchType("Top250${type.topPath}", type)
    data class Search(val search: String, val type: ContentType) :
        ContentFetchType("Search${type.searchPath}", type)
}