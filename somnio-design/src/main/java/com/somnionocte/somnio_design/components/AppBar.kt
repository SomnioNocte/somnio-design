package com.somnionocte.somnio_design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    start: @Composable RowScope.() -> Unit = {  },
    end: @Composable RowScope.() -> Unit = {  },
    center: @Composable RowScope.() -> Unit = {  },
) {
    ProvideTextStyle(remember { TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium) }) {
        Box(
            Modifier.fillMaxWidth().padding(32.dp, 16.dp).statusBarsPadding() then modifier
        ) {
            Row(
                Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically,
                content = start
            )

            Row(
                Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                content = center
            )

            Row(
                Modifier.align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically,
                content = end
            )
        }
    }
}