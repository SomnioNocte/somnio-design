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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.somnionocte.somnio_design.extensions.ListGroupBuilder
import com.somnionocte.somnio_design.extensions.ListGroupScope
import com.somnionocte.somnio_design.extensions.PositionInList
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
        shape = remember(positionInList) { defineVerticalShapeByPositionInList(positionInList) },
        contentPadding = PaddingValues(0.dp)
    ) {
        val verticalPadding = remember(positionInList) {
            if(positionInList == PositionInList.SingleItem) 18.dp else 16.dp
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
        shape = remember(positionInList) { defineVerticalShapeByPositionInList(positionInList) },
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier.weight(1f).padding(20.dp, 16.dp, 8.dp, 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    item.icon?.let { icon -> Icon(icon, null) }

                    Text(item.header, textAlign = TextAlign.Start, fontWeight = FontWeight.Medium)
                }

                item.description?.let {
                    Text(
                        it, Modifier.padding(top = 8.dp).fillMaxWidth(),
                        textAlign = TextAlign.Start, fontSize = 15.sp
                    )
                }
            }

            //Switch(item.state(), { item.onClick() }, Modifier.padding(start = 8.dp, end = 20.dp))
        }
    }
}

@Composable
private fun Spacer() {
    Spacer(Modifier.height(36.dp))
}

@Composable
fun VerticalList(
    modifier: Modifier = Modifier,
    items: ListGroupScope.Builder.() -> Unit
) {
    ListGroupBuilder(Orientation.Vertical, items, modifier) { item, positionInList ->
        when(item) {
            is ListGroupScope.Builder.Switch -> Switch(positionInList, item)
            is ListGroupScope.Builder.Button -> Button(positionInList, item)
            is ListGroupScope.Builder.Spacer -> Spacer()
            is ListGroupScope.Builder.Compose -> {
                val shape = remember(positionInList) { defineVerticalShapeByPositionInList(positionInList) }
                item.content(shape)
            }
        }
    }
}