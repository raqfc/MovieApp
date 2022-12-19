package br.com.raqfc.movieapp.presentation.contents.view_model

import android.icu.text.StringSearch
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.common.DataResource
import br.com.raqfc.movieapp.common.presentation.BaseNotifyingViewModel
import br.com.raqfc.movieapp.common.state.TextFieldState
import br.com.raqfc.movieapp.data.local.FavoriteContentsRepository
import br.com.raqfc.movieapp.data.network.ContentRepository
import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.domain.enums.ContentType
import br.com.raqfc.movieapp.presentation.contents.MainUiState
import br.com.raqfc.movieapp.presentation.contents.composables.ContentFetchMode
import br.com.raqfc.movieapp.presentation.contents.composables.ViewModeState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.Normalizer
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

    private val _searchState = mutableStateOf(TextFieldState(hint = R.string.contents_advanced_search_hint))
    var searchState: State<TextFieldState> = _searchState


    private var currentData: MutableList<ContentEntity> = mutableListOf()

    init {
        updateContent(false)
    }


    fun onSearchChanged(newValue: String) {
        _searchState.value = searchState.value.copy(
            text = newValue
        )
        if(viewModeState.value.fetchMode == ContentFetchMode.FAVORITES) {
            inMemorySearch(newValue)
        }
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
//        val newMode = ContentFetchMode.fromString(fetchMode)
        _viewModeState.value = viewModeState.value.copy(
            fetchMode = fetchMode
        )
        if(fetchMode == ContentFetchMode.TOP250) {
            updateContent()
        }
    }

    fun updateContent(
        forceRefresh: Boolean = false,
    ) {
        currentData = mutableListOf()
        val viewMode = viewModeState.value

        when(viewMode.fetchMode) {
            ContentFetchMode.TOP250 -> fetchContent(forceRefresh)
            ContentFetchMode.FAVORITES -> fetchAllFavorites()
            ContentFetchMode.SEARCH ->  mSearchContent()
        }
    }

    fun searchContent() {
        if(viewModeState.value.fetchMode == ContentFetchMode.SEARCH) {
            if(searchState.value.text.isBlank()) {
                _uiState.value = MainUiState.ShowError(R.string.message_toast_invalid_search)
            } else
                mSearchContent()
        } else if (viewModeState.value.fetchMode == ContentFetchMode.FAVORITES) {
            inMemorySearch(searchState.value.text)
        }
    }

    private fun inMemorySearch(search: String) {
        _state.value = DataResource.Success(currentData.filter {
            var normalizedSearch = Normalizer.normalize(search.trim(), Normalizer.Form.NFD)
            normalizedSearch = Regex("\\p{InCombiningDiacriticalMarks}+").replace(normalizedSearch, "")

            var normalizedTitle = Normalizer.normalize(it.title.trim(), Normalizer.Form.NFD)
            normalizedTitle = Regex("\\p{InCombiningDiacriticalMarks}+").replace(normalizedTitle, "")

            normalizedTitle.contains(normalizedSearch) || normalizedSearch.contains(normalizedTitle)
        })
    }

    private fun fetchAllFavorites() {
        baseGetContent {
            val favorites = favoritesRepository.getAllFavorites()
            contentRepository.fetchMultiple(favorites)
        }
    }

    private fun mSearchContent() {
        baseGetContent{  contentRepository.searchContent(viewModeState.value.contentType, searchState.value.text) }
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

            if(viewModeState.value.fetchMode == ContentFetchMode.FAVORITES) {
                inMemorySearch(searchState.value.text)
            }
        }
    }

    fun toggleFavorite(contentEntity: ContentEntity) {
        Log.e(" toggleFavorite", " toggleFavorite")
        execute(onError = {
            _uiState.value = MainUiState.ShowError()
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