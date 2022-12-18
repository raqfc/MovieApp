package br.com.raqfc.movieapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.raqfc.movieapp.common.presentation.Routes
import br.com.raqfc.movieapp.presentation.fullcontent.FullContentView
import br.com.raqfc.movieapp.presentation.contents.ContentsView
import br.com.raqfc.movieapp.ui.theme.VolanTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    @Inject lateinit var mainViewModel: MainViewModel

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VolanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Routes.Contents.route
                    ) {
                        composable(route = Routes.Contents.route,
                            enterTransition = {
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(350))
                            },
                            exitTransition = {
                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(350))
                            },
                            popEnterTransition = {
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(350))
                            },
                            popExitTransition = {
                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(350))
                            }
                        ) {
                            ContentsView(navController = navController)
                        }
                        composable(
                            route = Routes.ContentPage.route,
                            arguments = listOf(
                                navArgument(
                                    name = "contentId"
                                ) {
                                    type = NavType.StringType
                                }
                            ),
                            enterTransition = {
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(350))
                            },
                            exitTransition = {
                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(350))
                            },
                            popEnterTransition = {
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(350))
                            },
                            popExitTransition = {
                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(350))
                            }
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