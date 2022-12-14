/*
 * Copyright (c) 2021 onebone <me@onebone.me>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package br.com.justworks.volan2.commom.presentation.composables.scaffold

import ActionItem
import ActionMenu
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.justworks.volan2.R
import br.com.raqfc.movieapp.common.presentation.BaseUiEvent
import br.com.justworks.volan2.commom.presentation.composables.dialog.CustomAlertDialog
import br.com.justworks.volan2.commom.presentation.composables.dialog.ProgressDialog
import br.com.justworks.volan2.commom.presentation.composables.scaffold.CollapsingToolbarScaffoldScopeInstance.align
import br.com.justworks.volan2.commom.presentation.composables.scaffold.toolbar.*
import kotlin.math.max

@Stable
class CollapsingToolbarScaffoldState(
    val toolbarState: CollapsingToolbarState,
    initialOffsetY: Int = 0
) {
    val offsetY: Int
        get() = offsetYState.value

    internal val offsetYState = mutableStateOf(initialOffsetY)
}

private class CollapsingToolbarScaffoldStateSaver : Saver<CollapsingToolbarScaffoldState, Bundle> {
    override fun restore(value: Bundle): CollapsingToolbarScaffoldState =
        CollapsingToolbarScaffoldState(
            CollapsingToolbarState(value.getInt("height", Int.MAX_VALUE)),
            value.getInt("offsetY", 0)
        )

    override fun SaverScope.save(value: CollapsingToolbarScaffoldState): Bundle =
        Bundle().apply {
            putInt("height", value.toolbarState.height)
            putInt("offsetY", value.offsetY)
        }
}

@Composable
fun rememberCollapsingToolbarScaffoldState(
    toolbarState: CollapsingToolbarState = rememberCollapsingToolbarState()
): CollapsingToolbarScaffoldState {
    return rememberSaveable(toolbarState, saver = CollapsingToolbarScaffoldStateSaver()) {
        CollapsingToolbarScaffoldState(toolbarState)
    }
}

interface CollapsingToolbarScaffoldScope {
    fun Modifier.align(alignment: Alignment): Modifier
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingToolbarScaffold(
    modifier: Modifier = Modifier,
    toolbarModifier: Modifier = Modifier,

    showBackButton: Boolean = true,
    @DrawableRes backButtonIcon: Int = com.google.android.material.R.drawable.abc_vector_test,
    @StringRes titleRes: Int = R.string.app_name,
    actions: List<ActionItem> = listOf(),
    onUpClicked: () -> Unit = {},
    toolbar: (@Composable CollapsingToolbarScope.() -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    snackbarHost: @Composable () -> Unit = { },
    floatingActionButton: (@Composable () -> Unit)? = null,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(backgroundColor),

    uiEvents: BaseUiEvent?,

    state: CollapsingToolbarScaffoldState,
    scrollStrategy: ScrollStrategy = ScrollStrategy.ExitUntilCollapsed,
    enabled: Boolean = true,
    body: @Composable CollapsingToolbarScaffoldScope.() -> Unit
) {
    val flingBehavior = ScrollableDefaults.flingBehavior()
    val layoutDirection = LocalLayoutDirection.current

    val nestedScrollConnection = remember(scrollStrategy, state) {
        scrollStrategy.create(state.offsetYState, state.toolbarState, flingBehavior)
    }

    val toolbarState = state.toolbarState
    var mUiEvent by remember { mutableStateOf(uiEvents) }

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp

    Layout(
        content = {
            CollapsingToolbar(
                modifier = toolbarModifier,
                collapsingToolbarState = toolbarState
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceTint)
                        .fillMaxWidth()
                        .height(112.0.dp)// TopAppBarMediumTokens.ContainerHeight
                        .pin()
                )

                if (toolbar != null) {
                    toolbar()
                } else {
                    var actionsRowWidth by remember { mutableStateOf(0) }

                    val textSize = (18 + (30 - 18) * state.toolbarState.progress).sp
                    val titleBottomPadding = (16 * (1 - state.toolbarState.progress)).dp
                    val actionsFraction = if (state.toolbarState.progress == 1f) 0.9f else 0.1f

                    Text(
                        text = stringResource(id = titleRes),
                        modifier = Modifier
                            .road(
                                ViewConfiguration(Alignment.CenterStart, 120.dp),
                                ViewConfiguration(Alignment.BottomStart, 16.dp)
                            )
                            .padding(
                                top = 16.dp,
                                end = 16.dp,
                                bottom = titleBottomPadding
                            ),
                        color = Color.White,
                        fontSize = textSize
                    )

                    if (showBackButton)
                        IconButton(
                            onClick = onUpClicked,
                            modifier = Modifier
                                .pin(),
                        ) {
                            Icon(
                                painter = painterResource(id = backButtonIcon),
                                contentDescription = "back button"
                            )
                        }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(actionsFraction)
                            .animateContentSize(
                                animationSpec = spring(stiffness = StiffnessLow)
                            )
                            .onGloballyPositioned {
                                actionsRowWidth = it.size.width
                            }
                            .pin(Alignment.TopEnd),
                        horizontalArrangement = Arrangement.End
                    ) {
                        ActionMenu(
                            items = actions,
                            viewWidth = actionsRowWidth
                        )
                    }
                }
            }

            Scaffold(
                modifier,
                containerColor = backgroundColor,
                contentColor = contentColor,
                topBar = {  },
                bottomBar = bottomBar ?: {},
                floatingActionButton = floatingActionButton ?: {},
                snackbarHost = snackbarHost,
                floatingActionButtonPosition = floatingActionButtonPosition,
            ) {
                Box(modifier = Modifier.padding(it)) {
                    CollapsingToolbarScaffoldScopeInstance.body()
                }

                when (mUiEvent) {
                    is BaseUiEvent.ShowDialog -> {}
                    is BaseUiEvent.ShowError -> {
                        (mUiEvent as BaseUiEvent.ShowError).let { e ->
                            CustomAlertDialog(
                                content = e.error.errorMessage(),
                                onClick = {
                                    e.onClick()
                                    mUiEvent = null

                                },
                                onDismiss = {
                                    if (e.dismissible) {
                                        e.onDismissed()
                                        mUiEvent = null
                                    }
                                }
                            )
                        }
                    }
                    is BaseUiEvent.ShowProgressIndicator -> {
                        ProgressDialog()
                    }
                    null -> {}
                }
            }

        },
        modifier = modifier
            .then(
                if (enabled) {
                    Modifier.nestedScroll(nestedScrollConnection)
                } else {
                    Modifier
                }
            )
    ) { measurables, constraints ->
        check(measurables.size >= 2) {
            "the number of children should be at least 2: toolbar, (at least one) body"
        }

        val toolbarConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0
        )
        val bodyConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
            maxHeight = when (scrollStrategy) {
                ScrollStrategy.ExitUntilCollapsed ->
                    ((constraints.maxHeight - toolbarState.minHeight) - (toolbarState.height - toolbarState.minHeight )).coerceAtLeast(0)

                ScrollStrategy.EnterAlways, ScrollStrategy.EnterAlwaysCollapsed ->
                    constraints.maxHeight
            }
        )

        val toolbarPlaceable = measurables[0].measure(toolbarConstraints)

        val bodyMeasurables = measurables.subList(1, measurables.size)
        val childrenAlignments = bodyMeasurables.map {
            (it.parentData as? ScaffoldParentData)?.alignment
        }
        val bodyPlaceables = bodyMeasurables.map {
            it.measure(bodyConstraints)
        }

        val toolbarHeight = toolbarPlaceable.height

        val width = max(
            toolbarPlaceable.width,
            bodyPlaceables.maxOfOrNull { it.width } ?: 0
        ).coerceIn(constraints.minWidth, constraints.maxWidth)
        val height = max(
            toolbarHeight,
            bodyPlaceables.maxOfOrNull { it.height } ?: 0
        ).coerceIn(constraints.minHeight, constraints.maxHeight)

        layout(width, height) {
            bodyPlaceables.forEachIndexed { index, placeable ->
                val alignment = childrenAlignments[index]

                if (alignment == null) {
                    placeable.placeRelative(0, toolbarHeight + state.offsetY)
                } else {
                    val offset = alignment.align(
                        size = IntSize(placeable.width, placeable.height),
                        space = IntSize(width, height),
                        layoutDirection = layoutDirection
                    )
                    placeable.place(offset)
                }
            }
            toolbarPlaceable.placeRelative(0, state.offsetY)
        }
    }
}

internal object CollapsingToolbarScaffoldScopeInstance : CollapsingToolbarScaffoldScope {
    override fun Modifier.align(alignment: Alignment): Modifier =
        this.then(ScaffoldChildAlignmentModifier(alignment))
}

private class ScaffoldChildAlignmentModifier(
    private val alignment: Alignment
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any {
        return (parentData as? ScaffoldParentData) ?: ScaffoldParentData(alignment)
    }
}

private data class ScaffoldParentData(
    var alignment: Alignment? = null
)