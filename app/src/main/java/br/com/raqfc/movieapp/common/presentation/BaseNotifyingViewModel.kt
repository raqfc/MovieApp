package br.com.raqfc.movieapp.common.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raqfc.movieapp.common.DataResource
import kotlinx.coroutines.launch

abstract class BaseNotifyingViewModel<T>: ViewModel() {

    fun execute(eventFlow: MutableState<DataResource<T>>?, func: suspend () -> Unit) {
        execute(
            onStart = { eventFlow?.value = DataResource.Loading() },
            func = func,
            onError = { e -> eventFlow?.value = DataResource.Error(e) }
        )
    }

    fun execute(onStart: () ->Unit = {}, onError: (Exception) -> Unit = {}, func: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                onStart()
                func()
            } catch (e: Exception) {
                Log.e(this::class.java.simpleName, e.message ?: "")
                e.printStackTrace()
                onError(e)
            }
        }
    }

}