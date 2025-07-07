package com.somnionocte.somnio_design.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.somnionocte.somnio_design.extensions.ListGroupBuilder
import com.somnionocte.somnio_design.extensions.ListGroupScope
import com.somnionocte.somnio_design.extensions.PositionInList
import com.somnionocte.somnio_design.extensions.defineHorizontalShapeByPositionInList
import com.somnionocte.somnio_design.extensions.defineVerticalShapeByPositionInList

@Composable
private fun Button(
    positionInList: PositionInList,
    item: ListGroupScope.Builder.Button
) {
    SurfaceButton(
        onClick = item.onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = item.enabled,
        shape = defineVerticalShapeByPositionInList(positionInList),
        contentPadding = PaddingValues(0.dp)
    ) {
        val verticalPadding = remember(positionInList) {
            if(positionInList == PositionInList.SingleItem) 14.dp else 12.dp
        }

        Column(
            Modifier.fillMaxWidth().padding(horizontal = 6.dp).padding(bottom = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item.icon?.let { icon -> Icon(icon, null) }

                Text(
                    item.header,
                    Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = verticalPadding, bottom = verticalPadding - 5.dp)
                )
            }

            item.description?.let {
                Text(
                    it,
                    Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = verticalPadding, bottom = verticalPadding - 5.dp)
                )
            }

            //if(item.progress != null)
            //    LinearProgressIndicator(item.progress, Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun Switch(
    positionInList: PositionInList,
    item: ListGroupScope.Builder.Switch
) {
    SurfaceButton(
        onClick = item.onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = item.enabled,
        shape = defineVerticalShapeByPositionInList(positionInList),
        contentPadding = PaddingValues(0.dp)
    ) {
        val verticalPadding = remember(positionInList) {
            if(positionInList == PositionInList.SingleItem) 14.dp else 12.dp
        }

        Column(
            Modifier.fillMaxWidth().padding(horizontal = 6.dp).padding(bottom = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item.icon?.let { icon -> Icon(icon, null) }

                Text(
                    item.header,
                    Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = verticalPadding, bottom = verticalPadding - 5.dp)
                )
            }

            item.description?.let {
                Text(
                    it,
                    Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = verticalPadding, bottom = verticalPadding - 5.dp)
                )
            }
        }

        //Switch(item.state(), { item.onClick() })
    }
}


@Composable
private fun Spacer() {
    Spacer(Modifier.height(36.dp))
}

@Composable
fun HorizontalList(
    modifier: Modifier = Modifier,
    items: ListGroupScope.Builder.() -> Unit
) {
    ListGroupBuilder(Orientation.Horizontal, items, modifier) { item, positionInList ->
        when(item) {
            is ListGroupScope.Builder.Switch -> Switch(positionInList, item)
            is ListGroupScope.Builder.Button -> Button(positionInList, item)
            is ListGroupScope.Builder.Spacer -> Spacer()
            is ListGroupScope.Builder.Compose -> {
                val shape = remember(positionInList) { defineHorizontalShapeByPositionInList(positionInList) }
                item.content(shape)
            }
        }
    }
}