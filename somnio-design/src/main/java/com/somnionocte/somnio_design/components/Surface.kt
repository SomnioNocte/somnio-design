package com.somnionocte.somnio_design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.somnionocte.atomic_components.components.AtomicSurface
import com.somnionocte.somnio_design.SomnioTheme

@Composable
fun Surface(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(24.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    color: Color = SomnioTheme.colorScheme.lowSurface,
    shape: Shape = SomnioTheme.shapes.pretty,
    border: BorderStroke? = BorderStroke(1.dp, SomnioTheme.colorScheme.border),
    content: @Composable ColumnScope.() -> Unit
) {
    AtomicSurface(
        modifier = modifier,
        contentColor = SomnioTheme.colorScheme.onSurface,
        color = color,
        shape = shape,
        border = border
    ) {
        Column(
            Modifier.padding(contentPadding),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement
        ) {
            content()
        }
    }
}

@Composable
fun SurfaceContainer(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(16.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    shape: RoundedCornerShape = SomnioTheme.shapes.pretty,
    border: BorderStroke? = BorderStroke(1.dp, SomnioTheme.colorScheme.border),
    content: @Composable ColumnScope.() -> Unit
) {
    AtomicSurface(
        modifier = modifier,
        contentColor = SomnioTheme.colorScheme.onSurfaceContainer,
        color = SomnioTheme.colorScheme.surfaceContainer,
        shape = shape,
        border = border
    ) {
        Column(
            Modifier.padding(paddingValues),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement
        ) {
            content()
        }
    }
}