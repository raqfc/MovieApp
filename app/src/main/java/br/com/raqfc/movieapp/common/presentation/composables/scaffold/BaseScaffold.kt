@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.raqfc.movieapp.common.presentation.composables.scaffold

import ActionItem
import ActionMenu
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.common.presentation.composables.scaffold.toolbar.CollapsingToolbarScopeInstance.road
import br.com.raqfc.movieapp.common.presentation.composables.scaffold.toolbar.ViewConfiguration
import br.com.raqfc.movieapp.ui.theme.AppTheme

@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    showUpButton: Boolean = true,
    @StringRes titleRes: Int = R.string.app_name,
    actions: List<ActionItem> = listOf(),
    onUpClicked: () -> Unit = {},
    upIcon: ImageVector = Icons.Default.ArrowBack,
    showDefaultAppBar: Boolean = true,
    topBar: (@Composable () -> Unit) = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = { },
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    appBarColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable (PaddingValues) -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            if (!showDefaultAppBar) topBar()
            else {
                var actionsRowWidth by remember { mutableStateOf(0) }
                TopAppBar(
                    modifier = Modifier
                        .onGloballyPositioned {
                            actionsRowWidth = it.size.width
                        },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = appBarColor
                    ),
                    navigationIcon = {
                        if (showUpButton)
                            IconButton(
                                onClick = onUpClicked,
                                modifier = Modifier
                                    .padding(AppTheme.dimensions.padding2),
                            ) {
                                Icon(
                                    imageVector = upIcon,
                                    contentDescription = "back button",
                                    tint = titleColor
                                )
                            }
                    },
                    title = {
                        Text(
                            text = stringResource(id = titleRes),
                            modifier = Modifier
                                .road(
                                    ViewConfiguration(Alignment.CenterStart, 120.dp),
                                    ViewConfiguration(Alignment.BottomStart, 16.dp)
                                )
                                .padding(
                                    top = 16.dp, end = 16.dp, bottom = 16.dp
                                ),
                            color = titleColor,
                            fontSize = 18.sp
                        )
                    },
                    actions = {
                        ActionMenu(
                            items = actions, viewWidth = actionsRowWidth.toFloat()
                        )
                    },
                )
            }
        },
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = backgroundColor,
        contentColor = contentColor,
        content = content
    )
}