package br.com.raqfc.movieapp.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.raqfc.movieapp.ui.presentation.main.MainView
import br.com.raqfc.movieapp.common.presentation.Routes
import br.com.raqfc.movieapp.ui.presentation.content.FullContentView
import br.com.raqfc.movieapp.ui.theme.VolanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    @Inject lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VolanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Main.route
                    ) {
                        composable(route = Routes.Main.route) {
                            MainView(navController = navController)
                        }
                        composable(
                            route = Routes.ContentPage.route,
                            arguments = listOf(
                                navArgument(
                                    name = "contentId"
                                ) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val contentId = it.arguments?.getString("contentId") ?: ""
                            FullContentView(navController = navController, contentId)
                        }
                    }
                }
            }
        }
    }


}