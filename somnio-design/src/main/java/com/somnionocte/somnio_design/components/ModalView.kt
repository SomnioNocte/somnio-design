package com.somnionocte.somnio_design.components

import android.annotation.SuppressLint
import androidx.activity.compose.PredictiveBackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toOffset
import com.somnio_nocte.overscroll.bouncedOverscroll
import com.somnio_nocte.overscroll.delegateOverscroll
import com.somnio_nocte.portal.Portal
import com.somnionocte.compose_extensions.animatableAs
import com.somnionocte.compose_extensions.onPredictiveBack
import com.somnionocte.compose_extensions.plus
import com.somnionocte.compose_extensions.scale
import com.somnionocte.somnio_design.LocalColorScheme
import com.somnionocte.somnio_design.LocalContentColor
import com.somnionocte.somnio_design.LocalShapes
import com.somnionocte.somnio_design.LocalTypography
import com.somnionocte.somnio_design.SomnioTheme
import kotlin.math.absoluteValue
import kotlin.math.exp

@Immutable
class ModalViewHost {
    internal var isLinked = false
    internal var sourceOffsetState by mutableStateOf(Offset.Zero)
    internal var sourceSizeState by mutableStateOf(IntSize.Zero)

    @SuppressLint("UnusedBoxWithConstraintsScope")
    @Composable
    fun ModalView(
        onRequestDismiss: () -> Unit,
        modifier: Modifier = Modifier,
        key: Any = currentCompositeKeyHash,
        containerColor: Color = SomnioTheme.colorScheme.run { lerp(background, surfaceContainer, .25f) },
        overlayColor: Color = Color.Black.copy(.2f),
        shape: Shape = SomnioTheme.shapes.modalView,
        border: BorderStroke? = null,
        content: @Composable BoxScope.() -> Unit
    ) {
        val focus = LocalFocusManager.current

        Portal(
            key = key
        ) { transitionState ->
            val transition by transitionState.animateFloat({ spring(1f, 400f, .00000001f) }) { if(it) 1f else 0f }

            BoxWithConstraints(Modifier
                .fillMaxSize()
                .drawBehind { drawRect(overlayColor.copy(overlayColor.alpha * transition)) }
                .then(
                    if (transitionState.targetState)
                        Modifier.pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                focus.clearFocus()
                                onRequestDismiss()
                            })
                        }
                    else
                        Modifier
                )
            ) {
                val boxSize = with(LocalDensity.current) { DpSize(maxWidth, maxHeight).toSize() }

                val offset by transitionState.animateOffset({ spring(1f, 400f) }) {
                    if(isLinked) {
                        if(it) boxSize.center
                        else sourceOffsetState + sourceSizeState.center.toOffset().run {
                            Offset(x * 2f, y)
                        }
                    } else {
                        boxSize.center
                    }
                }

                Box(
                    Modifier
                        .graphicsLayer {
                            scale = transition
                            alpha = EaseInOutCubic.transform(transition)
                            translationX = offset.x - size.width * .5f
                            translationY = offset.y - size.height * .5f
                        }
                        then modifier
                        .safeDrawingPadding()
                        .padding(16.dp)
                        .background(containerColor, shape)
                        .pointerInput(Unit) { detectTapGestures() }
                        .then(
                            if (border != null) Modifier.border(border, shape)
                            else Modifier
                        ),
                    content = content
                )
            }
        }
    }
}

fun Modifier.modalViewSource(host: ModalViewHost) = composed {
    DisposableEffect(Unit) {
        host.isLinked = true
        onDispose { host.isLinked = false }
    }

    onGloballyPositioned {
        host.sourceOffsetState = it.positionInRoot()
        host.sourceSizeState = it.size
    }
}

@Composable
fun ModalView(
    onRequestDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    key: Any = currentCompositeKeyHash,
    containerColor: Color = SomnioTheme.colorScheme.run { lerp(background, surfaceContainer, .25f) },
    overlayColor: Color = Color.Black.copy(.2f),
    shape: Shape = SomnioTheme.shapes.modalView,
    border: BorderStroke? = null,
    scrollState: ScrollState = rememberScrollState(),
    content: @Composable BoxScope.() -> Unit
) {
    val focus = LocalFocusManager.current

    Portal(
        key = key
    ) { transitionState ->
        val predictiveGesture = onPredictiveBack(transitionState.targetState) { onRequestDismiss() }
        val transition by transitionState.animateFloat({ spring(1f, 300f, .00000001f) }) { if(it) 1f else 0f }
        val transitionBack by animatableAs(
            { if (predictiveGesture.isDragged) spring(1f, 3500f) else spring(1f, 300f) },
            value = { predictiveGesture.progress * .2f }
        ).asState()

        Box(Modifier
            .fillMaxSize()
            .drawBehind { drawRect(overlayColor.copy(overlayColor.alpha * transition)) }
            .then(
                if (transitionState.targetState)
                    Modifier.pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            focus.clearFocus()
                            onRequestDismiss()
                        })
                    }
                else
                    Modifier
            ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                Modifier
                    .bouncedOverscroll(scrollState)
                    .sizeIn(maxWidth = 500.dp)
                    .graphicsLayer {
                        translationY =
                            size.height * (EaseOut.transform(1f - transition) + transitionBack)
                    }
                    .then(modifier)
                    .fillMaxWidth()
                    .safeDrawingPadding()
                    .padding(16.dp)
                    .clip(shape)
                    .background(containerColor, shape)
                    .pointerInput(Unit) { detectTapGestures() }
                    .then(
                        if (border != null) Modifier.border(border, shape)
                        else Modifier
                    ),
                content = content
            )
        }
    }
}

@Composable
fun FullModalView(
    onRequestDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    key: Any = currentCompositeKeyHash,
    containerColor: Color = SomnioTheme.colorScheme.run { lerp(background, surfaceContainer, .25f) },
    overlayColor: Color = Color.Black.copy(.7f),
    shape: Shape = SomnioTheme.shapes.modalView,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    border: BorderStroke? = null,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable ColumnScope.() -> Unit
) {
    val focus = LocalFocusManager.current

    val colorScheme = LocalColorScheme.current
    val shapes = LocalShapes.current
    val typography = LocalTypography.current
    val textStyle = LocalTextStyle.current
    val contentColor = LocalContentColor.current

    Portal(key) { transitionState ->
        CompositionLocalProvider(
            LocalColorScheme provides colorScheme,
            LocalShapes provides shapes,
            LocalTypography provides typography,
            LocalTextStyle provides textStyle,
            LocalContentColor provides contentColor,
        ) {

            val predictiveGesture = onPredictiveBack(transitionState.targetState) { onRequestDismiss() }
            val transition by transitionState.animateFloat({ spring(1f, 300f, .00000001f) }) { if(it) 1f else 0f }
            val transitionBack by animatableAs(
                { if (predictiveGesture.isDragged) spring(1f, 3500f) else spring(1f, 300f) },
                value = { predictiveGesture.progress * .2f }
            ).asState()

            Box(Modifier
                .fillMaxSize()
                .drawBehind { drawRect(overlayColor.copy(overlayColor.alpha * transition)) }
                .then(
                    if (transitionState.targetState)
                        Modifier.pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                focus.clearFocus()
                                onRequestDismiss()
                            })
                        }
                    else
                        Modifier
                ),
                contentAlignment = Alignment.BottomCenter
            ) {
                val overscrollOffset = remember { Animatable(0f) }

                Surface(
                    Modifier
                        .delegateOverscroll(scrollState, overscrollOffset) {
                            focus.clearFocus()
                            onRequestDismiss()
                        }
                        .statusBarsPadding()
                        .fillMaxHeight()
                        .graphicsLayer {
                            translationY =
                                size.height * (EaseOut.transform(1f - transition) + transitionBack)

                            if(overscrollOffset.value > 0f)
                                translationY += overscrollOffset.value / exp(.5f)
                        }
                        .then(modifier)
                        .fillMaxWidth()
                        .verticalScroll(scrollState),
                    color = containerColor,
                    border = border,
                    shape = shape,
                    contentPadding = contentPadding +
                            WindowInsets.navigationBars.asPaddingValues() +
                            WindowInsets.ime.asPaddingValues()
                ) {
                    Column(
                        Modifier.graphicsLayer {
                            if(overscrollOffset.value < 0f)
                                translationY -= overscrollOffset.value.absoluteValue / exp(.5f)
                        },
                        horizontalAlignment = horizontalAlignment,
                        verticalArrangement = verticalArrangement,
                        content = content
                    )
                }
            }
        }
    }
}