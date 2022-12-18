package br.com.raqfc.movieapp.ui.presentation.content

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import br.com.raqfc.movieapp.common.DataResource
import br.com.raqfc.movieapp.common.presentation.BaseNotifyingViewModel
import br.com.raqfc.movieapp.data.local.FavoriteContentsRepository
import br.com.raqfc.movieapp.data.network.ContentRepository
import br.com.raqfc.movieapp.domain.entities.FullContentEntity
import javax.inject.Inject

class ContentViewModel @Inject constructor(val contentRepository: ContentRepository, val favoritesRepository: FavoriteContentsRepository,savedStateHandle: SavedStateHandle): BaseNotifyingViewModel<FullContentEntity>() {
    private val _state = mutableStateOf<DataResource<FullContentEntity>>(DataResource.Loading())
    val state: State<DataResource<FullContentEntity>> = _state

    init {
        savedStateHandle.get<String>("contentId")?.let { contentId ->
            if(contentId.isNotBlank()) {
               getContentById(contentId)
            } else {
                _state.value = DataResource.Error(Exception("Error obtaining content"))
            }
        }
    }

    fun getContentById(contentId: String) {
        execute(_state) {
            val content = contentRepository.fetchItem(contentId)
            content.fold(onSuccess = {
                _state.value = DataResource.Success(it)
            }, onFailure = {
                throw it
            })

        }
    }

}