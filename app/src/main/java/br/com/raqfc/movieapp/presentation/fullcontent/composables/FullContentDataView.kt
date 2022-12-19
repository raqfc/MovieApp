package br.com.raqfc.movieapp.presentation.fullcontent.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.domain.entities.FullContentEntity
import br.com.raqfc.movieapp.ui.theme.AppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun FullContentDataView(content: FullContentEntity) {
    var posterSize by remember { mutableStateOf(IntSize(0, 0)) }
    var posterPosition by remember { mutableStateOf(Offset(0f, 0f)) }

    val scrollState = rememberScrollState()

    Column(
        Modifier
            .verticalScroll(scrollState)
            .padding(bottom = AppTheme.dimensions.padding6)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    posterSize = it.size
                    posterPosition = it.positionInWindow()
                }
                .padding(AppTheme.dimensions.padding3),
            model = ImageRequest.Builder(LocalContext.current)
                .data(content.image)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
        )

        SelectionContainer( modifier = Modifier.fillMaxWidth()) {
            Text(
                text = content.fullTitle,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.SansSerif
            )
        }

        Row(
            modifier = Modifier
                .padding(top = AppTheme.dimensions.padding2)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = AppTheme.dimensions.padding2),
                text = content.year
            )
            Text(text = stringResource(id = R.string.full_content_dot_separator))
            Text(
                modifier = Modifier
                    .padding(horizontal = AppTheme.dimensions.padding2),
                text = content.contentRating
            )
            if(content.duration != null) {
                Text(text = stringResource(id = R.string.full_content_dot_separator))
                Text(
                    modifier = Modifier
                        .padding(horizontal = AppTheme.dimensions.padding2),
                    text = content.duration!!
                )
            }
        }


        Text(
            modifier = Modifier
                .padding(top = AppTheme.dimensions.padding4)
                .padding(horizontal = AppTheme.dimensions.padding4),
            text = content.plot
        )

        if (content.ratings != null) {


            Text(
                modifier = Modifier
                    .padding(top = AppTheme.dimensions.padding4)
                    .padding(horizontal = AppTheme.dimensions.padding4),
                fontWeight = FontWeight.Black,
                text = stringResource(id = R.string.full_content_ratings)
            )

            Row(
                Modifier
                    .padding(top = AppTheme.dimensions.padding2)
                    .padding(horizontal = AppTheme.dimensions.padding6),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.imdb_logo),
                    modifier = Modifier
                        .height(12.dp)
                        .padding(end = AppTheme.dimensions.padding3),
                    contentScale = ContentScale.Fit,
                    contentDescription = "imdb logo"
                )

                Text(
                    text = stringResource(
                        id = R.string.full_content_rating_imdb,
                        content.ratings?.imDb!!
                    )
                )
            }

            Row(
                Modifier
                    .padding(top = AppTheme.dimensions.padding2)
                    .padding(horizontal = AppTheme.dimensions.padding6),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rotten_tomatoes__logo),
                    modifier = Modifier
                        .height(16.dp)
                        .padding(end = AppTheme.dimensions.padding3),
                    contentScale = ContentScale.Fit,
                    contentDescription = "rotten tomatoes logo"
                )

                Text(
                    text = stringResource(
                        id = R.string.full_content_rating_rotten_tomatoes,
                        content.ratings?.rottenTomatoes!!
                    )
                )
            }
        }

        if(!content.directors.isNullOrBlank()) {
            Text(
                modifier = Modifier
                    .padding(top = AppTheme.dimensions.padding4)
                    .padding(horizontal = AppTheme.dimensions.padding4),
                fontWeight = FontWeight.Black,
                text = stringResource(id = R.string.full_content_directors)
            )
            Text(
                modifier = Modifier
                    .padding(top = AppTheme.dimensions.padding2)
                    .padding(horizontal = AppTheme.dimensions.padding6),
                text = content.directors!!
            )

        }

        if(!content.writers.isNullOrBlank()) {
            Text(
                modifier = Modifier
                    .padding(top = AppTheme.dimensions.padding4)
                    .padding(horizontal = AppTheme.dimensions.padding4),
                fontWeight = FontWeight.Black,
                text = stringResource(id = R.string.full_content_writers)
            )
            Text(
                modifier = Modifier
                    .padding(top = AppTheme.dimensions.padding2)
                    .padding(horizontal = AppTheme.dimensions.padding6),
                text = content.writers!!
            )
        }


        if (content.awards != null) {
            Text(
                modifier = Modifier
                    .padding(top = AppTheme.dimensions.padding4)
                    .padding(horizontal = AppTheme.dimensions.padding4),
                fontWeight = FontWeight.Black,
                text = stringResource(id = R.string.full_content_awards)
            )
            Text(
                modifier = Modifier
                    .padding(top = AppTheme.dimensions.padding2)
                    .padding(horizontal = AppTheme.dimensions.padding6),
                text = content.awards ?: ""
            )

        }

        if (content.trailer != null) {
            Text(
                modifier = Modifier
                    .padding(top = AppTheme.dimensions.padding4)
                    .padding(horizontal = AppTheme.dimensions.padding4),
                fontWeight = FontWeight.Black,
                text = stringResource(id = R.string.full_content_trailer)
            )
            val uriHandler = LocalUriHandler.current
            BoxWithConstraints(Modifier.clickable {
                if (!content.trailer?.link.isNullOrBlank())
                    uriHandler.openUri(content.trailer?.link!!)
            }) {
                Card(
                    Modifier
                        .wrapContentSize()
                        .padding(top = AppTheme.dimensions.padding2)
                        .padding(horizontal = AppTheme.dimensions.padding6)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(content.trailer?.thumbnailUrl!!)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                    )
                }

                Box(modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.Center)
                    .background(Color(0xAA444444), RoundedCornerShape(16.dp))) {
                    Icon(
                        modifier = Modifier
                            .size(AppTheme.dimensions.actionSize),
                        imageVector = Icons.Default.PlayArrow,
                        tint = Color.White,
                        contentDescription = "play arrow"
                    )
                }
            }
        }


    }
}