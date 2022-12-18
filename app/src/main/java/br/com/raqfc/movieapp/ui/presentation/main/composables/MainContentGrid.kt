package br.com.raqfc.movieapp.ui.presentation.main.composables

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import br.com.raqfc.movieapp.common.presentation.composables.listing.BaseLazyVerticalGrid
import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.ui.theme.AppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContentGrid(contentList: List<ContentEntity>, onExpandContent: (ContentEntity) -> Unit) {
    BaseLazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(AppTheme.dimensions.padding4),
        content = {
            items(contentList.size) {
                Card(
                    modifier = Modifier.padding(AppTheme.dimensions.padding3).background(Color.Red),
                    onClick = {
                        onExpandContent(contentList[it])
                    },
                    //fix
                    colors = CardDefaults.cardColors(
                        //todo selection color
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            AppTheme.dimensions.elevation1
                        )
                    ),
                    elevation = CardDefaults.cardElevation()
                ) {
                    //Modifier.padding(AppTheme.dimensions.padding4)
                    Column() {
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth(),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(contentList[it].image)
                                .crossfade(true)
                                .build(),
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth,
                        )
                    }

                }


            }
        })
}
