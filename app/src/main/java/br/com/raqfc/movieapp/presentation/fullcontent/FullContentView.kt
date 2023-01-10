package br.com.raqfc.movieapp.presentation.fullcontent

import ActionItem
import OverflowMode
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.common.presentation.composables.Information
import br.com.raqfc.movieapp.common.presentation.composables.scaffold.CollapsingToolbarScaffold
import br.com.raqfc.movieapp.common.presentation.composables.scaffold.rememberCollapsingToolbarScaffoldState
import br.com.raqfc.movieapp.presentation.fullcontent.composables.FullContentDataView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullContentView(
    navController: NavController,
    contentId: String,
    contentViewModel: ContentViewModel = hiltViewModel()
) {
    val scaffoldState = rememberCollapsingToolbarScaffoldState()


    var actions: List<ActionItem> by remember {
        mutableStateOf(listOf())
    }
    var title by remember {
        mutableStateOf("")
    }


    when (val uiState = contentViewModel.uiState.value) {
        is FullContentUiState.UpdateToolbar -> {
            actions = listOf(
                ActionItem(
                    R.string.main_tab_favorite_action,
                    (if (uiState.fullContentEntity.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder),
                    OverflowMode.IF_NECESSARY,
                    contentViewModel::toggleFavorite,
                    iconColor = Color.Red
                )
            )
            title = uiState.fullContentEntity.title
            contentViewModel.clearUiState()
        }
        FullContentUiState.ShowError -> {
            Toast.makeText(
                LocalContext.current,
                R.string.error_information_content,
                Toast.LENGTH_LONG
            ).show()
            contentViewModel.clearUiState()
        }
        else -> {}
    }

    CollapsingToolbarScaffold(
        showUpButton = true,
        upIcon = Icons.Default.KeyboardArrowUp,
        title = title,
        onUpClicked = { navController.navigateUp() },
        actions = actions,
        state = scaffoldState
    ) {
        when (val state = contentViewModel.state.value) {
            is DataResource.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Information(
                        Modifier.align(Alignment.Center),
                        showTryAgain = true,
                        onTryAgain = { contentViewModel.getContentById(contentId) })
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
                FullContentDataView(state.data)
            }
        }
    }
}

