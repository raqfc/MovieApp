package br.com.justworks.volan2.commom.presentation.composables.listing

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.justworks.volan2.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun BaseListItem(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        onClick = onClick,
        colors = cardColors(
            //todo selection color
            containerColor = if (checked) Color.Black.copy(alpha = 0.4f) else MaterialTheme.colorScheme.surfaceColorAtElevation(
                AppTheme.dimensions.elevation1
            )
        )
    ) {
        Row(
            Modifier
                .defaultMinSize(
                    minHeight = AppTheme.dimensions.cardMinHeight
                )
                .padding(
                    horizontal = AppTheme.dimensions.padding4,
                    vertical = AppTheme.dimensions.padding3
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}