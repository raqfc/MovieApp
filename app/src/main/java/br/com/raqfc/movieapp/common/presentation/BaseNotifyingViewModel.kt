package br.com.raqfc.movieapp.common.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raqfc.movieapp.common.ListResource
import kotlinx.coroutines.launch

abstract class BaseNotifyingViewModel<T>: ViewModel() {
    fun execute(eventFlow: MutableState<ListResource<T>>, func: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                eventFlow.value = ListResource.Loading()
                func()
            } catch (e: Exception) {
                eventFlow.value = ListResource.Error(e)
            }
        }
    }

}