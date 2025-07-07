package com.somnionocte.somnio_design.components

import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.somnionocte.atomic_components.components.AtomicLinearIndeterminateProgress
import com.somnionocte.compose_extensions.animatableAs
import com.somnionocte.compose_extensions.scale
import com.somnionocte.somnio_design.SomnioTheme
import kotlin.math.abs

@Composable
fun LinearIndeterminateProgress(
    modifier: Modifier = Modifier.padding(vertical = 8.dp)
) {
    val isDarkTheme = SomnioTheme.colorScheme.background.luminance() > .5f

    AtomicLinearIndeterminateProgress(
        SomnioTheme.colorScheme.lowPrimary.copy(if(isDarkTheme) .5f else 1f),
        SomnioTheme.colorScheme.primary,
        modifier,
        shapeRadius = 15.dp,
        width = 200.dp
    )
}

@Composable
fun DottedIndeterminateProgress(
    modifier: Modifier = Modifier.padding(vertical = 8.dp),
    dotColor: Color = SomnioTheme.colorScheme.onSurface,
    width: Dp = 160.dp,
    height: Dp = 8.dp,
    count: Int = (width.value / 32).toInt().coerceAtLeast(1)
) {
    val animationState = rememberInfiniteTransition()
    val transition by animationState.animateFloat(-1f, 5f, infiniteRepeatable(tween(1500, 0, EaseInOut)))

    Row(
        Modifier.defaultMinSize(width, height) then modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        repeat(count) { index ->
            val scaleTransition by animatableAs({ spring(.35f, 150f) }) {
                lerp(.35f, 1f, abs(transition - index).coerceIn(0f..1f))
            }.asState()

            Box(Modifier.size(height).graphicsLayer{
                scale = scaleTransition
            }.background(dotColor, RoundedCornerShape(50)))
        }
    }
}