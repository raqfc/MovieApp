package br.com.raqfc.movieapp.presentation.contents

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.common.DataResource
import br.com.raqfc.movieapp.common.presentation.Routes
import br.com.raqfc.movieapp.common.presentation.composables.DefaultTextField
import br.com.raqfc.movieapp.common.presentation.composables.Information
import br.com.raqfc.movieapp.common.presentation.composables.listing.BaseLazyVerticalGrid
import br.com.raqfc.movieapp.domain.enums.ContentType
import br.com.raqfc.movieapp.presentation.contents.composables.*
import br.com.raqfc.movieapp.presentation.contents.view_model.MainViewModel
import br.com.raqfc.movieapp.ui.theme.AppTheme


@Composable
fun ContentsView(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabItems = listOf(
        TabBarItem(
            R.string.main_tab_movies,
            Icons.Outlined.Movie,
            Icons.Filled.Movie,
            ContentType.Movie
        ),
        TabBarItem(
            R.string.main_tab_tv,
            Icons.Outlined.LiveTv,
            Icons.Filled.LiveTv,
            ContentType.Tv
        ),
        TabBarItem(
            R.string.main_tab_favorites,
            iconUnselected = Icons.Default.FavoriteBorder,
            iconSelected = Icons.Default.Favorite,
            ContentType.AllFavorites
        )
    )
    val focusManager = LocalFocusManager.current

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

        when (mainViewModel.uiState.value) {
            is MainUiState.ShowError -> {
                Toast.makeText(
                    LocalContext.current,
                    (mainViewModel.uiState.value as MainUiState.ShowError).messageRes,
                    Toast.LENGTH_LONG
                ).show()
                mainViewModel.clearUiState()
            }
            else -> {}
        }

        val viewModeState =
            mainViewModel.viewModeState.value

        Row(
            Modifier.fillMaxWidth().wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (viewModeState.fetchMode == ContentFetchMode.SEARCH || viewModeState.contentType == ContentType.AllFavorites) {
                DefaultTextField(
                    Modifier.padding(AppTheme.dimensions.padding3).weight(1f, true),
                    state = mainViewModel.searchState.value,
                    onValueChange = mainViewModel::onSearchChanged,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    singleLine = true,
                    keyboardActions = KeyboardActions(onSearch = {
                        focusManager.clearFocus()
                        mainViewModel.searchContent()
                    }),
                )
            } else {
                Box(modifier = Modifier.width(0.5.dp))
            }

            if (viewModeState.contentType != ContentType.AllFavorites) {
                ContentFetchModeSelector(
                    mainViewModel.viewModeState.value.fetchMode,
                    mainViewModel::changeFetchMode,
                )
            } else {
                Box(modifier = Modifier.width(0.5.dp))
            }
        }

        when (val state = mainViewModel.state.value) {
            is DataResource.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Information(Modifier.align(Alignment.Center), showTryAgain = true, onTryAgain = { mainViewModel.updateContent(true) })
                }

            }
            is DataResource.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
            is DataResource.Success -> {
                if (state.data.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Information(
                            Modifier.align(Alignment.Center),
                            contentMessage = if (viewModeState.contentType == ContentType.AllFavorites)
                                if (mainViewModel.searchState.value.text.isNotBlank())
                                    R.string.information_empty_favorites_searched
                                else
                                    R.string.information_empty_favorites else
                                R.string.information_empty_content,
                            showTryAgain = false,
                            onTryAgain = { mainViewModel.updateContent(true) })
                    }
                } else {
                    BaseLazyVerticalGrid(columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(AppTheme.dimensions.padding4),
                        content = {
                            items(state.data.size, key = {
                                state.data[it].id
                            }) {
                                MainContentGridItem(
                                    state.data[it],
                                    showFavoriteButton = viewModeState.contentType != ContentType.AllFavorites,
                                    onExpandContent = { c ->
                                        navController.navigate(
                                            Routes.ContentPage.route.replace(
                                                oldValue = "{contentId}", newValue = c.id
                                            )
                                        )
                                    },
                                    onToggleFavorite = mainViewModel::toggleFavorite
                                )
                            }
                        })
                }
            }
        }
    }
}
