package br.com.raqfc.movieapp.common.presentation

sealed class Routes(val route: String) {
    object Contents: Routes("content")
    object ContentPage: Routes("content/{contentId}")
}