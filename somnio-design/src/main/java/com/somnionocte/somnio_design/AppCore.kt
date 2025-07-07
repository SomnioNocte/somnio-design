package com.somnionocte.somnio_design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.somnio_nocte.portal.NexusPortal

@Composable
fun AppCore(
    content: @Composable () -> Unit
) {
    Box(Modifier.fillMaxSize().background(Color.Black)) {
        SomnioTheme {
            NexusPortal {
                content()
            }
        }
    }
}