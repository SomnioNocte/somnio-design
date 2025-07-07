package com.somnionocte.somnio_design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.somnionocte.somnio_design.SomnioTheme

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = SomnioTheme.colorScheme.onLowPrimary.copy(.35f),
    height: Dp = 1.dp,
    padding: PaddingValues = PaddingValues(16.dp, 2.dp)
) {
    Box(modifier.padding(padding).fillMaxWidth().height(height).background(color))
}