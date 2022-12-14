package br.com.justworks.volan2.commom.presentation.composables.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.justworks.volan2.R
import br.com.raqfc.movieapp.ui.theme.base.LocalDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressDialog(
    @StringRes title: Int = R.string.default_loading
) {
    Dialog(
        onDismissRequest = { },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card {
            Column(
                modifier = Modifier
                    .padding(vertical = LocalDimensions.current.padding5)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text(text = stringResource(id = title), Modifier.padding(top = LocalDimensions.current.padding4))
            }
        }
    }
}