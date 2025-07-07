package com.somnionocte.somnio_design

import android.os.Build
import android.view.RoundedCorner
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp

@Immutable
data class Shapes(
    val normal: RoundedCornerShape = RoundedCornerShape(20.dp),
    val pretty: RoundedCornerShape = RoundedCornerShape(28.dp),
    val modalView: RoundedCornerShape = RoundedCornerShape(40.dp),
    val relativePretty: RoundedCornerShape = RoundedCornerShape(46),
) {
    val screen: RoundedCornerShape
        @Composable
        get() {
            val screenRadiusCorner = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                LocalView.current.display.getRoundedCorner(RoundedCorner.POSITION_TOP_LEFT)
                    ?.radius?.minus(4f)?.coerceAtLeast(0f) ?: 0f
            } else {
                0f
            }

            return RoundedCornerShape(with(LocalDensity.current) { screenRadiusCorner.toDp() })
        }
}

val LocalShapes = staticCompositionLocalOf { Shapes() }