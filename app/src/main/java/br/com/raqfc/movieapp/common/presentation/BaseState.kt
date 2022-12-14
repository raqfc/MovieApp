package br.com.raqfc.movieapp.common.presentation

abstract class BaseState<T: BaseState<T>> {

    abstract fun isValid(): Boolean
    abstract fun focusOnFirstInvalid()
    abstract fun forceValidate(): T
}