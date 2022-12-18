package br.com.raqfc.movieapp.common.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raqfc.movieapp.common.DataResource
import kotlinx.coroutines.launch

abstract class BaseNotifyingViewModel<T>: ViewModel() {
    fun execute(eventFlow: MutableState<DataResource<T>>, func: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                eventFlow.value = DataResource.Loading()
                func()
            } catch (e: Exception) {
                eventFlow.value = DataResource.Error(e)
            }
        }
    }

}