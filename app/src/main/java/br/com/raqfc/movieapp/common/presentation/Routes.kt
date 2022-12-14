package br.com.raqfc.movieapp.common.presentation

sealed class Routes(val route: String) {
//    object Splash: Routes("splash")
//    object OnBoarding: Routes("onBoarding")
//    object Login: Routes("login")
    object Main: Routes("main")
//    object RecoverPassword: Routes("recoverPassword")
}