package br.com.raqfc.movieapp.ui.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.common.DataResource
import br.com.raqfc.movieapp.common.presentation.Routes
import br.com.raqfc.movieapp.common.presentation.composables.Information
import br.com.raqfc.movieapp.domain.enums.ContentType
import br.com.raqfc.movieapp.ui.presentation.main.composables.MainContentGrid
import br.com.raqfc.movieapp.ui.presentation.main.composables.TabBarItem
import br.com.raqfc.movieapp.ui.presentation.main.view_model.MainViewModel


@Composable
fun MainView(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabItems = listOf(
        TabBarItem(
            R.string.main_tab_movies,
            Icons.Outlined.Movie,
            Icons.Filled.Movie,
            ContentType.MOVIE
        ),
        TabBarItem(R.string.main_tab_tv, Icons.Outlined.LiveTv, Icons.Filled.LiveTv, ContentType.TV)
    )

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        mainViewModel.changeContentType(item.contentType)
                        selectedTabIndex = index
                    },
                    icon = {
                        Icon(
                            imageVector = if (selectedTabIndex == index) item.iconSelected else item.iconUnselected,
                            contentDescription = "Movie icon"
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(id = item.titleRes),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                )
            }
        }

        when (val state = mainViewModel.state.value) {
            is DataResource.Error -> {
                //error = state.e,
                Information(showTryAgain = true, onTryAgain = { mainViewModel.getContent(true) })
            }
            is DataResource.Loading -> {
                CircularProgressIndicator()
            }
            is DataResource.Success -> {
                if (state.data.isEmpty()) {
                    Information(
                        contentMessage = R.string.information_empty_content,
                        showTryAgain = true,
                        onTryAgain = { mainViewModel.getContent(true) })
                } else {
                    MainContentGrid(state.data, onExpandContent = {
                        navController.navigate(
                            Routes.ContentPage.route
                                .replace(
                                    oldValue = "{contentId}",
                                    newValue = it.id
                                )
                        )
                    })
                }
            }
        }
    }
}
