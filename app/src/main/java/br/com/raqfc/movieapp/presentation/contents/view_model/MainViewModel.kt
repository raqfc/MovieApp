package br.com.raqfc.movieapp.presentation.contents.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import br.com.raqfc.movieapp.common.DataResource
import br.com.raqfc.movieapp.common.presentation.BaseNotifyingViewModel
import br.com.raqfc.movieapp.data.local.FavoriteContentsRepository
import br.com.raqfc.movieapp.data.network.ContentRepository
import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType
import br.com.raqfc.movieapp.presentation.contents.MainUiState
import br.com.raqfc.movieapp.presentation.contents.composables.ContentFetchMode
import br.com.raqfc.movieapp.presentation.contents.composables.ViewModeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    val contentRepository: ContentRepository,
    val favoritesRepository: FavoriteContentsRepository,
) : BaseNotifyingViewModel<List<ContentEntity>>() {

    private val _viewModeState =
        mutableStateOf(ViewModeState(ContentType.Movie, ContentFetchMode.TOP250))
    var viewModeState: State<ViewModeState> = _viewModeState

    private val _uiState = mutableStateOf<MainUiState>(MainUiState.Default)
    val uiState: State<MainUiState> = _uiState


    private val _state =
        mutableStateOf<DataResource<List<ContentEntity>>>(DataResource.Success(mutableListOf()))
    val state: State<DataResource<List<ContentEntity>>> = _state


    private var currentData: MutableList<ContentEntity> = mutableListOf()

    init {
        updateContent(false)
    }

    fun changeContentType(contentType: ContentType?) {
        if(contentType == null)
            _viewModeState.value = viewModeState.value.copy(
                fetchMode = ContentFetchMode.FAVORITES
            )
        else
            _viewModeState.value = viewModeState.value.copy(
                fetchMode = ContentFetchMode.TOP250,
                contentType = contentType
            )
        updateContent()
    }

    fun changeFetchMode(fetchMode: ContentFetchMode) {
        _viewModeState.value = viewModeState.value.copy(
            fetchMode = fetchMode
        )
    }

    fun updateContent(
        forceRefresh: Boolean = false,
    ) {
        currentData = mutableListOf()
        val viewMode = viewModeState.value

        when(viewMode.fetchMode) {
            ContentFetchMode.TOP250 -> fetchContent(forceRefresh)
            ContentFetchMode.FAVORITES -> fetchAllFavorites()
            ContentFetchMode.SEARCH ->  searchContent()
        }

    }
    private fun fetchAllFavorites() {
        baseGetContent {
            val favorites = favoritesRepository.getAllFavorites()
            contentRepository.fetchMultiple(favorites)
        }
    }

    private fun searchContent() {
        baseGetContent{  contentRepository.searchContent(viewModeState.value.contentType, "alo") }
    }

    private fun fetchContent(forceRefresh: Boolean) {
        baseGetContent {
            contentRepository.getContent(
                forceRefresh,
                viewModeState.value.contentType
            )
        }
    }
    private fun baseGetContent(func: suspend () -> Result<MutableList<ContentEntity>>) {
        execute(_state) {
            val favorites = favoritesRepository.getAllFavorites()
            val data =
                func()
                    .fold(onSuccess = {
                        it
                    }, onFailure = {
                        throw it
                    })

            data.forEach {
                it.isFavorite = favorites.contains(it.id)
            }
            currentData = data
            _state.value = DataResource.Success(data = data)
            Log.e("MainViewModel -> getContent success:", data.toString())
        }
    }

    fun toggleFavorite(contentEntity: ContentEntity) {
        Log.e(" toggleFavorite", " toggleFavorite")
        execute(onError = {
            _uiState.value = MainUiState.ShowError
            contentEntity.isFavorite = !contentEntity.isFavorite
        }) {
            contentEntity.isFavorite = !contentEntity.isFavorite
            favoritesRepository.setFavorite(contentEntity.id, contentEntity.isFavorite)
            _uiState.value = MainUiState.UpdateItem(contentEntity)
            currentData.find { it.id == contentEntity.id }?.isFavorite = contentEntity.isFavorite
            _state.value = DataResource.Success(data = currentData)
        }
    }

    fun clearUiState() {
        _uiState.value = MainUiState.Default
    }

}