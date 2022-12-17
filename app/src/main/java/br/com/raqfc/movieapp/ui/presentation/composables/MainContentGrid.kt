package br.com.raqfc.movieapp.ui.presentation.composables

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.raqfc.movieapp.common.presentation.composables.listing.BaseLazyVerticalGrid
import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.ui.theme.AppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainContentGrid(contentList: List<ContentEntity>) {
//    var selected: ContentEntity? by remember {
//        mutableStateOf(null)
//    }
//    val sheetState = rememberModalBottomSheetState(
//        initialValue = ModalBottomSheetValue.Hidden,
//        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
//    )
//    val coroutineScope = rememberCoroutineScope()
//
//    BackHandler(sheetState.isVisible) {
//        coroutineScope.launch { sheetState.hide() }
//    }
//
//    ModalBottomSheetLayout(
//        sheetState = sheetState,
//        sheetContent = { ContentSheet(selected) },
//    ) {
        BaseLazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(AppTheme.dimensions.padding4),
            content = {
                items(contentList.size) {

                    Card(
                        modifier = Modifier.padding(AppTheme.dimensions.padding3),
                        onClick = {
//                            selected = contentList[it]
//                            coroutineScope.launch {
//                                if (sheetState.isVisible) sheetState.hide()
//                                else sheetState.show()
//                            }
                        },
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
//    }

}

@Composable
fun ContentSheet(contentEntity: ContentEntity?) {
    if(contentEntity == null) return
    Column(Modifier.padding(AppTheme.dimensions.padding8)) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(contentEntity.image)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
        )

        Text(text = contentEntity.fullTitle)
        Text(text = contentEntity.crew)
        Text(text = contentEntity.rank)
    }


}