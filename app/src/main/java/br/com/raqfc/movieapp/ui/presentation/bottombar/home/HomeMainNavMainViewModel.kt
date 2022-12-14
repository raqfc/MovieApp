package br.com.justworks.volan2.main.presentation.bottombar.home

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeMainNavMainViewModel @Inject constructor() : ViewModel() {
    var listState: LazyListState? = null

    @SuppressLint("MutableCollectionMutableState")
    private val _elements = MutableList(100) { false }.toMutableStateList()
    val elements: List<Boolean> = _elements

    fun onElementClick(index: Int) {
        _elements[index] = !_elements[index]
    }

}