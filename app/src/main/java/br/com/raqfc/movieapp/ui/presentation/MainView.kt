package br.com.raqfc.movieapp.ui.presentation

import ActionItem
import OverflowMode
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.justworks.volan2.commom.presentation.composables.scaffold.CollapsingToolbarScaffold
import br.com.justworks.volan2.commom.presentation.composables.scaffold.rememberCollapsingToolbarScaffoldState
import br.com.justworks.volan2.main.presentation.bottombar.MainNavigationBarItems
import br.com.raqfc.movieapp.R
import br.com.raqfc.movieapp.ui.presentation.view_model.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    val state = rememberCollapsingToolbarScaffoldState()

    val actions = listOf(
        ActionItem(R.string.a, Icons.Default.Call, OverflowMode.IF_NECESSARY) {},
        ActionItem(R.string.b, Icons.Default.Send, OverflowMode.IF_NECESSARY) {},
        ActionItem(R.string.c, Icons.Default.Email, OverflowMode.IF_NECESSARY) {},
        ActionItem(R.string.d, Icons.Default.Delete, OverflowMode.IF_NECESSARY) {},
    )
    mainViewModel.getContent()
//
//    val scope = rememberCoroutineScope()
//    scope.launch {
//
//
//    }

    val navItems by remember { mutableStateOf(MainNavigationBarItems.getNavItems()) }
    var selectedIndex by remember { mutableStateOf(0) }

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        uiEvents = null,
        state = state,
        bottomBar = {
            NavigationBar {
                for ((index, item) in navItems.withIndex()) {
                    val label = stringResource(id = item.labelRes)
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(item.iconVector, contentDescription = label)
                        },
                        label = {
                            Text(
                                text = label,
                                softWrap = false,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                fontSize = MaterialTheme.typography.labelSmall.fontSize
                            )
                        }
                    )
                }
            }
        },
        actions = actions
    ) {
        navItems[selectedIndex].view()
    }
}