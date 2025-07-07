package com.somnionocte.somnio_design.extensions

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class ListGroupScope {
    sealed class Item

    inner class Builder {
        private val rawItems = mutableListOf<Item>()
        internal fun build(block: Builder.() -> Unit): List<Item> {
            block()
            return rawItems
        }

        inner class Spacer : Item() {
            init { rawItems.add(this) }
        }

        inner class Compose(
            val content: @Composable (shape: Shape) -> Unit
        ) : Item() {
            init { rawItems.add(this) }
        }

        inner class Button(
            val header: String,
            val icon: ImageVector? = null,
            val description: String? = null,
            val enabled: Boolean = true,
            val progress: (() -> Float)? = null,
            val onClick: () -> Unit
        ) : Item() {
            init { rawItems.add(this) }
        }

        inner class Switch(
            val state: () -> Boolean,
            val header: String,
            val icon: ImageVector? = null,
            val description: String? = null,
            val enabled: Boolean = true,
            val onClick: () -> Unit,
        ) : Item() {
            init { rawItems.add(this) }
        }
    }

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items = _items.asStateFlow()

    @Composable
    internal fun collect(content: Builder.() -> Unit) {
        LaunchedEffect(content) {
            snapshotFlow { Builder().build(content) }.collectLatest {
                _items.emit(it)
            }
        }
    }
}

@Composable
fun rememberListGroupScope(
    items: ListGroupScope.Builder.() -> Unit
): StateFlow<List<ListGroupScope.Item>> {
    val scope = remember { ListGroupScope() }
    scope.collect(items)
    return scope.items
}

@Composable
inline fun ListGroupBuilder(
    orientation: Orientation,
    noinline items: ListGroupScope.Builder.() -> Unit,
    modifier: Modifier = Modifier,
    gap: Dp = 3.dp,
    crossinline compose: @Composable (item: ListGroupScope.Item, positionInList: PositionInList) -> Unit
) {
    val items by rememberListGroupScope(items).collectAsStateWithLifecycle()

    SubcomposeLayout(modifier) { constraints ->
        val fixedConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        val placeables = items
            .flatMapIndexed { index, it ->
                subcompose(it.hashCode()) {
                    val isPreviousGap = items.getOrNull(index - 1) is ListGroupScope.Builder.Spacer
                    val isNextGap = items.getOrNull(index + 1) is ListGroupScope.Builder.Spacer

                    val lastIndex = if(isNextGap || isPreviousGap)
                        if(isPreviousGap || items.lastIndex == index) 0
                        else index
                    else
                        items.lastIndex

                    val positionInList = definePositionInList(
                        index = if(isPreviousGap) 0 else index,
                        lastIndex = lastIndex
                    )

                    compose(it, positionInList)

                    if(positionInList == PositionInList.Middle || positionInList == PositionInList.First) {
                        if(orientation == Orientation.Vertical)
                            Spacer(Modifier.height(gap))
                        else
                            Spacer(Modifier.width(gap))
                    }
                }
            }
            .fastMap { it.measure(fixedConstraints) }

        if(orientation == Orientation.Vertical) {
            val totalHeight = placeables.sumOf { it.height }

            layout(constraints.maxWidth, totalHeight) {
                var yOffset = 0
                placeables.fastForEach { placeable ->
                    placeable.placeRelative(0, yOffset)
                    yOffset += placeable.height
                }
            }
        } else {
            val totalWidth = placeables.sumOf { it.width }

            layout(totalWidth, constraints.maxHeight) {
                var xOffset = 0
                placeables.fastForEach { placeable ->
                    placeable.placeRelative(xOffset, 0)
                    xOffset += placeable.width
                }
            }
        }
    }
}