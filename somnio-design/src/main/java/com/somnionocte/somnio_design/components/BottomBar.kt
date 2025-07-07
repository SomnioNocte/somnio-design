package com.somnionocte.somnio_design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.somnionocte.somnio_design.SomnioTheme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    start: @Composable RowScope.() -> Unit = {  },
    end: @Composable RowScope.() -> Unit = {  },
    center: @Composable RowScope.() -> Unit
) {
    Box(
        modifier
            .fillMaxWidth().widthIn(max = 380.dp).navigationBarsPadding().padding(32.dp, 24.dp)
            .background(SomnioTheme.colorScheme.border, RoundedCornerShape(45))
            .padding(horizontal = 14.dp).height(56.dp)
    ) {
        Row(
            Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = center
        )

        Row(
            Modifier.align(Alignment.CenterStart),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = start
        )

        Row(
            Modifier.align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = end
        )
    }
}