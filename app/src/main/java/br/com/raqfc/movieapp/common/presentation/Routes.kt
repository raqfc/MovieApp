package br.com.raqfc.movieapp.common.presentation

sealed class Routes(val route: String) {
    object Main: Routes("content")
    object ContentPage: Routes("content/{contentId}")
}