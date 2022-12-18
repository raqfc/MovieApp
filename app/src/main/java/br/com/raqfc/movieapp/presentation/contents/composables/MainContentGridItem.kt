package br.com.raqfc.movieapp.presentation.contents.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import br.com.raqfc.movieapp.common.presentation.composables.scaffold.toolbar.CollapsingToolbarScopeInstance.pin
import br.com.raqfc.movieapp.domain.entities.ContentEntity
import br.com.raqfc.movieapp.ui.theme.AppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContentGridItem(content: ContentEntity, showFavoriteButton: Boolean, onExpandContent: (ContentEntity) -> Unit, onToggleFavorite: (ContentEntity) -> Unit) {
    Card(
        modifier = Modifier.padding(AppTheme.dimensions.padding3),
        onClick = {
            onExpandContent(content)
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation()
    ) {
        //Modifier.padding(AppTheme.dimensions.padding4)
        Box(Modifier.wrapContentSize()) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(content.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
            )

            if(showFavoriteButton)
                IconButton(modifier = Modifier.pin(Alignment.TopEnd), onClick = { onToggleFavorite(content) }) {
                    Icon(
                        imageVector = if (content.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        tint = Color.Red,
                        contentDescription = "favorite"
                    )
                }
        }

    }
}
