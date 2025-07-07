package com.somnionocte.somnio_design

import androidx.compose.foundation.LocalIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import com.somnionocte.atomic_components.core.ScaleIndication
import com.somnionocte.somnio_design.components.LocalTextStyle

@Composable
fun SomnioTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalShapes provides remember { Shapes() },
        LocalTypography provides remember { Typography() },
        LocalColorScheme provides rememberColorScheme()
    ) {
        CompositionLocalProvider(
            LocalIndication provides ScaleIndication,
            LocalContentColor provides LocalColorScheme.current.onBackground,
            LocalTextStyle provides LocalTypography.current.body,
            content = content
        )
    }
}

object SomnioTheme {
    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current
}

val LocalContentColor = com.somnionocte.atomic_components.core.LocalContentColor