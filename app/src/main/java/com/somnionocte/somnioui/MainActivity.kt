package com.somnionocte.somnioui

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.delete
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowOutward
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Euro
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Work
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import com.somnionocte.compose_extensions.VRoundedCornerShape
import com.somnionocte.screen_router.NullScreen
import com.somnionocte.screen_router.ScreenRouter
import com.somnionocte.somnio_design.AppCore
import com.somnionocte.somnio_design.ColorScheme
import com.somnionocte.somnio_design.LocalColorScheme
import com.somnionocte.somnio_design.SomnioTheme
import com.somnionocte.somnio_design.WithColorScheme
import com.somnionocte.somnio_design.components.ActionButton
import com.somnionocte.somnio_design.components.BottomRow
import com.somnionocte.somnio_design.components.Button
import com.somnionocte.somnio_design.components.FullModalView
import com.somnionocte.somnio_design.components.Icon
import com.somnionocte.somnio_design.components.IconButton
import com.somnionocte.somnio_design.components.IconLowButton
import com.somnionocte.somnio_design.components.IconSurfaceButton
import com.somnionocte.somnio_design.components.LowButton
import com.somnionocte.somnio_design.components.ModalViewHost
import com.somnionocte.somnio_design.components.Scaffold
import com.somnionocte.somnio_design.components.Surface
import com.somnionocte.somnio_design.components.SurfaceButton
import com.somnionocte.somnio_design.components.Text
import com.somnionocte.somnio_design.components.TextField
import com.somnionocte.somnio_design.components.modalViewSource
import com.somnionocte.somnio_design.extensions.PositionInList
import com.somnionocte.somnio_design.extensions.defineHorizontalShapeByPositionInList
import com.somnionocte.somnio_design.extensions.defineVerticalShapeByPositionInList
import com.somnionocte.somnio_design.rememberColorScheme

object Screens {

}

class AppState(application: Application) : AndroidViewModel(application) {

}

class MainActivity : ComponentActivity() {
    val appState by viewModels<AppState>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppCore {
                ScreenRouter { screen -> when(screen) {
                    is NullScreen -> MainScreen()
                } }
            }
        }
    }

    @Composable
    fun MainScreen() {
        val scrollState = rememberScrollState()

        Scaffold(
            scrollState = scrollState
        ) { innerPadding ->

        }
    }
}