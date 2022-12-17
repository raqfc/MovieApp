package br.com.raqfc.movieapp.common.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.common.presentation.composables.DefaultButton
import br.com.raqfc.movieapp.ui.theme.AppTheme

@Composable
fun Information(
    modifier: Modifier = Modifier,
//    error: Exception,
    imageVector: ImageVector = Icons.Default.Error,
    @StringRes titleMessage: Int = R.string.error_information_title,
    @StringRes contentMessage: Int = R.string.error_information_content,
    @StringRes contentDescription: Int = R.string.error_information_default_content_description,
    showTryAgain: Boolean = false,
    @StringRes tryAgainMessage: Int = R.string.try_again,
    onTryAgain: () -> Unit = {}
) {
//todo create custom error wrapper to parse error messages from it
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.dimensions.padding8)
            .padding(top = AppTheme.dimensions.padding8)
            .then(modifier), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = stringResource(id = contentDescription)
        )

        Text(
            modifier = Modifier.padding(top = AppTheme.dimensions.padding3),
            text = stringResource(id = titleMessage),
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            modifier = Modifier.padding(top = AppTheme.dimensions.padding3),
            text = stringResource(id = contentMessage),
            style = MaterialTheme.typography.labelSmall
        )

        if (showTryAgain) {
            DefaultButton(
                modifier = Modifier.padding(top = AppTheme.dimensions.padding3),
                onClick = onTryAgain
            ) {
                Text(text = stringResource(id = tryAgainMessage))
            }
        }
    }
}