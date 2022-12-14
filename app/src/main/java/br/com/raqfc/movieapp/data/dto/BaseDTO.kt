package br.com.raqfc.movieapp.data.dto

abstract class BaseDTO<E> {
    abstract fun toEntity(): E?
}