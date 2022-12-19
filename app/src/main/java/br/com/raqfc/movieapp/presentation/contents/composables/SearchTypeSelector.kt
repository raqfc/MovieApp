package br.com.raqfc.movieapp.presentation.contents.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.common.presentation.composables.scaffold.CollapsingToolbarScaffoldScopeInstance.align

@Composable
fun ContentFetchModeSelector(
    selectedMode: ContentFetchMode,
    onModeChanged: (ContentFetchMode) -> Unit
) {
    val menuVisible: MutableState<Boolean> = remember { mutableStateOf(false) }
    val values = hashMapOf(
        ContentFetchMode.TOP250 to stringResource(id = R.string.fetch_mode_top_250),
        ContentFetchMode.SEARCH to stringResource(id = R.string.fetch_mode_advanced_search),
    )
    Row(
        Modifier
            .wrapContentWidth()
            .align(Alignment.CenterEnd)) {
        TextButton(modifier = Modifier, onClick = { menuVisible.value = true }) {
            Icon(
                Icons.Default.Sort,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "More actions"
            )
            Text(text = values[selectedMode] ?: "")
        }

        DropdownMenu(
            expanded = menuVisible.value,
            onDismissRequest = { menuVisible.value = false },
        ) {
            for (value in values) {
                key(value.hashCode()) {
                    DropdownMenuItem(
                        text = { Text(value.value) },
                        onClick = {
                            menuVisible.value = false
                            onModeChanged(value.key)
                        })
                }
            }
        }
    }
}