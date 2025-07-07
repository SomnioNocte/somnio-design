package com.somnionocte.somnio_design.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.somnio_nocte.overscroll.bouncedOverscroll
import com.somnionocte.atomic_components.components.AtomicScaffold
import com.somnionocte.atomic_components.components.AtomicSurface
import com.somnionocte.compose_extensions.minus
import com.somnionocte.compose_extensions.plus
import com.somnionocte.somnio_design.SomnioTheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import kotlin.math.exp

@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    top: @Composable (() -> Unit)? = null,
    bottom: @Composable (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable (PaddingValues) -> Unit
) {
    val hazeState = remember { HazeState() }

    val color = SomnioTheme.colorScheme.background

    val brush = remember(color) { Brush.verticalGradient(
        listOf(
            color,
            color,
            Color.Transparent
        ),
        tileMode = TileMode.Decal
    ) }

    AtomicSurface(
        contentColor = SomnioTheme.colorScheme.onBackground,
        color = SomnioTheme.colorScheme.background,
    ) {
        AtomicScaffold(
            modifier = modifier,
            top = top?.let { { Box(Modifier.hazeEffect(hazeState) {
                backgroundColor = color
                blurRadius = 18.dp
                noiseFactor = 0f
                mask = brush
            }.fillMaxWidth().statusBarsPadding()) { it() } } }
                ?: { BlurStatusBar(hazeState) { true } },
            bottom = bottom ?: { Spacer(Modifier.navigationBarsPadding()) },
            content = { Box(Modifier.hazeSource(hazeState)) { content(it + contentPadding) } }
        )
    }

}

@Composable
fun BlurStatusBar(hazeState: HazeState, enabled: () -> Boolean) {
    val color = SomnioTheme.colorScheme.background

    val brush = remember(color) { Brush.verticalGradient(
        listOf(
            color,
            color.copy(.8f),
            Color.Transparent
        ),
        tileMode = TileMode.Decal
    ) }

    Box(Modifier
        .hazeEffect(hazeState) {
            blurEnabled = enabled()
            backgroundColor = color
            blurRadius = 18.dp
            noiseFactor = 0f
            mask = brush
        }
        .drawBehind { drawRect(brush, alpha = .5f) }
        .padding(vertical = 24.dp)
        .statusBarsPadding()
        .fillMaxWidth())
}

@Composable
fun Scaffold(
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
    top: @Composable (() -> Unit)? = null,
    bottom: @Composable (() -> Unit)? = null,
    showScrollbar: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable (PaddingValues) -> Unit
) {
    val hazeState = remember { HazeState() }
    val overscrollOffset = remember { Animatable(0f) }

    val overscrollModifier = remember(overscrollOffset) {
        Modifier.graphicsLayer { translationY = overscrollOffset.value / exp(2f) }
    }

    val statusPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    val color = SomnioTheme.colorScheme.background

    val brush = remember(color) { Brush.verticalGradient(
        listOf(
            color,
            color.copy(.8f),
            Color.Transparent
        ),
        tileMode = TileMode.Decal
    ) }

    AtomicSurface(
        contentColor = SomnioTheme.colorScheme.onBackground,
        color = SomnioTheme.colorScheme.background,
    ) {
        AtomicScaffold(
            modifier = if(showScrollbar)
                modifier.scrollbar(
                    scrollState,
                    PaddingValues(
                        top = statusPadding,
                        start = 10.dp,
                        bottom = navPadding,
                        end = 10.dp,
                    )
                ) {
                    overscrollOffset.value
                }
            else modifier,
            top =
                top?.let { { Box(Modifier.hazeEffect(hazeState) {
                    backgroundColor = color
                    blurRadius = 18.dp
                    noiseFactor = 0f
                    mask = brush
                }.then(overscrollModifier).fillMaxWidth()) { it() } } }
                    ?: { BlurStatusBar(hazeState) { scrollState.canScrollBackward } },
            bottom = bottom?.let { { Box(overscrollModifier) { it() } } } ?: { Box(Modifier.navigationBarsPadding()) },
            content = { Box(Modifier
                .hazeSource(hazeState)
                .bouncedOverscroll(scrollState, overscrollOffset)
            ) { content(
                if(top != null) it + contentPadding
                else it - PaddingValues(
                    top = if(it.calculateTopPadding() > statusPadding) statusPadding
                    else it.calculateTopPadding()
                ) + contentPadding
            ) } }
        )
    }
}

@Composable
fun Scaffold(
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
    top: @Composable (() -> Unit)? = null,
    bottom: @Composable (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable (PaddingValues) -> Unit
) {
    val hazeState = remember { HazeState() }
    val overscrollOffset = remember { Animatable(0f) }

    val overscrollModifier = remember(overscrollOffset) {
        Modifier.graphicsLayer { translationY = overscrollOffset.value / exp(2f) }
    }

    val color = SomnioTheme.colorScheme.background

    val brush = remember(color) { Brush.verticalGradient(
        listOf(
            color,
            color.copy(.8f),
            Color.Transparent
        ),
        tileMode = TileMode.Decal
    ) }

    AtomicSurface(
        contentColor = SomnioTheme.colorScheme.onBackground,
        color = SomnioTheme.colorScheme.background,
    ) {
        AtomicScaffold(
            modifier = modifier.bouncedOverscroll(scrollState),
            top =
                top?.let { { Box(Modifier.hazeEffect(hazeState) {
                    backgroundColor = color
                    blurRadius = 18.dp
                    noiseFactor = 0f
                    mask = brush
                }.then(overscrollModifier).fillMaxWidth()) { it() } } }
                    ?: { BlurStatusBar(hazeState) { scrollState.canScrollBackward } },
            bottom = bottom?.let { { Box(overscrollModifier) { it() } } } ?: { Box(Modifier.navigationBarsPadding()) },
            content = { Box(Modifier
                .hazeSource(hazeState)
                .bouncedOverscroll(scrollState, overscrollOffset)
                .graphicsLayer { translationY = overscrollOffset.value / exp(1f) }
            ) { content(it + contentPadding) } }
        )
    }
}

@Composable
fun Scaffold(
    scrollState: LazyGridState,
    modifier: Modifier = Modifier,
    top: @Composable (() -> Unit)? = null,
    bottom: @Composable (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable (PaddingValues) -> Unit
) {
    val hazeState = remember { HazeState() }
    val overscrollOffset = remember { Animatable(0f) }

    val overscrollModifier = remember(overscrollOffset) {
        Modifier.graphicsLayer { translationY = overscrollOffset.value / exp(2f) }
    }

    val color = SomnioTheme.colorScheme.background

    val brush = remember(color) { Brush.verticalGradient(
        listOf(
            color,
            color.copy(.8f),
            Color.Transparent
        ),
        tileMode = TileMode.Decal
    ) }

    AtomicSurface(
        contentColor = SomnioTheme.colorScheme.onBackground,
        color = SomnioTheme.colorScheme.background,
    ) {
        AtomicScaffold(
            modifier = modifier.bouncedOverscroll(scrollState),
            top =
                top?.let { { Box(Modifier.hazeEffect(hazeState) {
                    backgroundColor = color
                    blurRadius = 18.dp
                    noiseFactor = 0f
                    mask = brush
                }.then(overscrollModifier).fillMaxWidth()) { it() } } }
                    ?: { BlurStatusBar(hazeState) { scrollState.canScrollBackward } },
            bottom = bottom?.let { { Box(overscrollModifier) { it() } } } ?: { Box(Modifier.navigationBarsPadding()) },
            content = { Box(Modifier
                .hazeSource(hazeState)
                .bouncedOverscroll(scrollState, overscrollOffset)
                .graphicsLayer { translationY = overscrollOffset.value / exp(1f) }
            ) { content(it + contentPadding) } }
        )
    }
}