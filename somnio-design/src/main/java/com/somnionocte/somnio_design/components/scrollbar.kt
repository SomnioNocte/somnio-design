package com.somnionocte.somnio_design.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import com.somnionocte.atomic_components.components.coreScrollbar
import com.somnionocte.somnio_design.SomnioTheme

fun Modifier.scrollbar(
    scrollState: ScrollState,
    contentPadding: PaddingValues = PaddingValues(10.dp),
    overscrollOffset: (() -> Float)? = null
) = composed {
    coreScrollbar(
        scrollState,
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding(),
            bottom = contentPadding.calculateBottomPadding(),
            start = 10.dp,
            end = 10.dp
        ),
        color = SomnioTheme.colorScheme.onLowPrimary.copy(.5f),
        overscrollOffset = overscrollOffset
    )
}