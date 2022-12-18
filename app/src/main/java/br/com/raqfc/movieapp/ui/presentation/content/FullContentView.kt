package br.com.raqfc.movieapp.ui.presentation.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.raqfc.movieapp.common.DataResource
import br.com.raqfc.movieapp.common.presentation.composables.Information
import br.com.raqfc.movieapp.ui.theme.AppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun FullContentView(
    navController: NavController,
    contentId: String,
    contentViewModel: ContentViewModel = hiltViewModel()
) {

    Column(Modifier.padding(AppTheme.dimensions.padding8)) {
        when (val state = contentViewModel.state.value) {
            is DataResource.Error -> {
                //error = state.e,
                Information(showTryAgain = true, onTryAgain = { contentViewModel.getContentById(contentId) })
            }
            is DataResource.Loading -> {
                CircularProgressIndicator()
            }
            is DataResource.Success -> {
                val data = state.data
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                )

                Text(text = state.data.fullTitle)
                Text(text = state.data.crew)
                Text(text = state.data.rank)
            }
        }
    }
}