package com.somnionocte.somnio_design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomRow(
    modifier: Modifier = Modifier,
    primaryAction: @Composable RowScope.() -> Unit = {  },
    secondaryAction: @Composable RowScope.() -> Unit = {  },
    tertiaryAction: @Composable RowScope.() -> Unit = {  },
) {
    Box(
        Modifier.fillMaxWidth().padding(32.dp, 16.dp).navigationBarsPadding() then modifier
    ) {
        Row(
            Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
            content = secondaryAction
        )

        Row(
            Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            content = primaryAction
        )

        Row(
            Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
            content = tertiaryAction
        )
    }
}