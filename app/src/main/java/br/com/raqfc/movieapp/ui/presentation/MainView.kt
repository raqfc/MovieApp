package br.com.raqfc.movieapp.ui.presentation

import ActionItem
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.common.presentation.composables.listing.BaseLazyColumn
import br.com.raqfc.movieapp.common.presentation.composables.listing.BaseListItem
import br.com.raqfc.movieapp.common.presentation.composables.scaffold.CollapsingToolbarScaffold
import br.com.raqfc.movieapp.common.presentation.composables.scaffold.rememberCollapsingToolbarScaffoldState
import br.com.raqfc.movieapp.ui.presentation.view_model.MainViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    val state = rememberCollapsingToolbarScaffoldState()
    val actions = listOf(
        ActionItem(R.string.a, Icons.Default.Call, OverflowMode.IF_NECESSARY) {},
        ActionItem(R.string.b, Icons.Default.Send, OverflowMode.IF_NECESSARY) {},
        ActionItem(R.string.c, Icons.Default.Email, OverflowMode.IF_NECESSARY) {},
        ActionItem(R.string.d, Icons.Default.Delete, OverflowMode.IF_NECESSARY) {},
    )


    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        uiEvents = null,
        state = state,
        actions = actions
    ) {
        BaseLazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
//            state = mListState
        ) {
            item {
                Text(text = "home")
            }
            items(100) {

                BaseListItem(
                    checked = false,
//                modifier = modifier,
                    onClick = { }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://avatars.githubusercontent.com/u/64285399?s=400&u=eb0133cf08db378ea27e611dc9048b9ff2dc1a64&v=4")
                            .crossfade(true)
                            .build(),
//                placeholder = painterResource(placeholderImageRes),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = "Item $it",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}