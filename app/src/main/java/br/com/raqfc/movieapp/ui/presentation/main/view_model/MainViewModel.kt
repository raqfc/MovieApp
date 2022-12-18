package br.com.raqfc.movieapp.ui.presentation.main.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import br.com.raqfc.movieapp.common.DataResource
import br.com.raqfc.movieapp.common.presentation.BaseNotifyingViewModel
import br.com.raqfc.movieapp.data.local.FavoriteContentsRepository
import br.com.raqfc.movieapp.data.network.ContentRepository
import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType
import br.com.raqfc.movieapp.ui.presentation.main.composables.ContentFetchMode
import br.com.raqfc.movieapp.ui.presentation.main.composables.ViewModeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    val contentRepository: ContentRepository,
    val favoritesRepository: FavoriteContentsRepository,
) : BaseNotifyingViewModel<List<ContentEntity>>() {

    private val _viewModeState = mutableStateOf(ViewModeState(ContentType.MOVIE, ContentFetchMode.TOP250, false))
    var viewModeState: State<ViewModeState> = _viewModeState
//
//    private val _eventFlow = MutableSharedFlow<ListResource<ContentEntity>>()
//    val eventFlow = _eventFlow.asSharedFlow()


    private val _state = mutableStateOf<DataResource<List<ContentEntity>>>(DataResource.Success(mutableListOf()))
    val state: State<DataResource<List<ContentEntity>>> = _state

    init {
        getContent(false)
    }

    fun changeContentType(contentType: ContentType) {
        _viewModeState.value = viewModeState.value.copy(
            contentType = contentType
        )
        getContent()
    }

    fun changeFetchMode(fetchMode: ContentFetchMode) {
        _viewModeState.value = viewModeState.value.copy(
            fetchMode = fetchMode
        )
    }

    fun getContent(
        forceRefresh: Boolean = false,
    ) {
        execute(_state) {
            val favorites = favoritesRepository.getAllFavorites(viewModeState.value.contentType)
            val data =
                contentRepository.getContent(forceRefresh, viewModeState.value.contentType).fold(onSuccess = {
                    it
                }, onFailure = {
                    throw it
                })

            data.forEach {
                it.isFavorite = favorites.contains(it.id)
            }
            _state.value = DataResource.Success(data = data)
            Log.e("MainViewModel -> getContent success:", data.toString())
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