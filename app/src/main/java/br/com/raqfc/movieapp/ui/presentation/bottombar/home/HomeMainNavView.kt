package br.com.raqfc.movieapp.ui.presentation.bottombar.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.raqfc.movieapp.common.presentation.composables.listing.BaseLazyColumn
import br.com.raqfc.movieapp.common.presentation.composables.listing.BaseListItem
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HomeMainNavView(
    viewModel: HomeMainNavMainViewModel = hiltViewModel()
) {
    if (viewModel.listState == null) {
        viewModel.listState = rememberLazyListState()
    }
    val mListState by remember { mutableStateOf(viewModel.listState!!) }

    val listItemsState = viewModel.elements

    BaseLazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        state = mListState
    ) {
        item {
            Text(text = "home")
        }
        items(100) {

            BaseListItem(
                checked = false,
//                modifier = modifier,
                onClick = { viewModel.onElementClick(it) }
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