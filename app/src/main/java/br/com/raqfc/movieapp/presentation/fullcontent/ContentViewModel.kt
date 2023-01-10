package br.com.raqfc.movieapp.presentation.fullcontent

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import br.com.raqfc.movieapp.common.presentation.BaseNotifyingViewModel
import br.com.raqfc.movieapp.data.local.FavoriteContentsRepository
import br.com.raqfc.movieapp.data.network.ContentRepository
import br.com.raqfc.movieapp.domain.entities.FullContentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository,
    private val favoritesRepository: FavoriteContentsRepository,
    savedStateHandle: SavedStateHandle
) : BaseNotifyingViewModel<FullContentEntity>() {
    private val _state = mutableStateOf<DataResource<FullContentEntity>>(DataResource.Loading())
    val state: State<DataResource<FullContentEntity>> = _state

    private val _uiState = mutableStateOf<FullContentUiState>(FullContentUiState.Default)
    val uiState: State<FullContentUiState> = _uiState

    init {
        savedStateHandle.get<String>("contentId")?.let { contentId ->
            if (contentId.isNotBlank()) {
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
                it.isFavorite = favoritesRepository.isFavorite(contentId)
                _uiState.value = FullContentUiState.UpdateToolbar(it)
                _state.value = DataResource.Success(it)
            }, onFailure = {
                throw it
            })

        }
    }

    fun toggleFavorite() {
        val contentEntity =
            (state.value as? DataResource.Success<FullContentEntity>)?.data ?: return
        execute(onError = { _uiState.value = FullContentUiState.ShowError }) {
            contentEntity.isFavorite = !contentEntity.isFavorite
            favoritesRepository.setFavorite(contentEntity.id, contentEntity.isFavorite)
            _state.value = DataResource.Success(contentEntity)
            _uiState.value = FullContentUiState.UpdateToolbar(contentEntity)
        }
    }

    fun clearUiState() {
        _uiState.value = FullContentUiState.Default
    }

}