package com.somnionocte.somnio_design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.somnionocte.atomic_components.templates.TemplateButton
import com.somnionocte.somnio_design.SomnioTheme

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(16.dp, 4.dp),
    shape: RoundedCornerShape = SomnioTheme.shapes.pretty,
    border: BorderStroke? = BorderStroke(1.dp, SomnioTheme.colorScheme.border),
    content: @Composable RowScope.() -> Unit
) {
    TemplateButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        borderStroke = border,
        defaultColor = SomnioTheme.colorScheme.surfaceContainer,
        pressedColor = SomnioTheme.colorScheme.run { lerp(surfaceContainer, onSurfaceContainer, .1f) },
        contentColor = SomnioTheme.colorScheme.onSurfaceContainer,
        shape = shape,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(16.dp, 8.dp),
    shape: RoundedCornerShape = RoundedCornerShape(49),
    content: @Composable RowScope.() -> Unit
) {
    TemplateButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        defaultColor = Color.Transparent,
        pressedColor = SomnioTheme.colorScheme.primary.copy(.35f),
        contentColor = SomnioTheme.colorScheme.primary,
        shape = shape,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        contentPadding = contentPadding,
        minSize = DpSize(Dp.Unspecified, 28.dp),
        content = content
    )
}

@Composable
fun IconSurfaceButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(16.dp, 8.dp),
    shape: RoundedCornerShape = SomnioTheme.shapes.relativePretty,
    content: @Composable RowScope.() -> Unit
) {
    TemplateButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        defaultColor = SomnioTheme.colorScheme.run { remember(SomnioTheme.colorScheme) { lerp(lowSurface, primary, .075f) } },
        pressedColor = SomnioTheme.colorScheme.run { remember(SomnioTheme.colorScheme) { lerp(surfaceContainer, primary, .3f) } },
        contentColor = SomnioTheme.colorScheme.primary,
        shape = shape,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = contentPadding,
        minSize = DpSize(Dp.Unspecified, 28.dp),
        content = content
    )
}

private val actionButtonTextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium)

@Composable
fun ActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(20.dp, 14.dp, 24.dp, 14.dp),
    shape: RoundedCornerShape = SomnioTheme.shapes.relativePretty,
    content: @Composable RowScope.() -> Unit
) {
    ProvideTextStyle(actionButtonTextStyle) {
        TemplateButton(
            modifier = modifier,
            onClick = onClick,
            enabled = enabled,
            defaultColor = SomnioTheme.colorScheme.primary,
            pressedColor = SomnioTheme.colorScheme.run { lerp(primary, onPrimary, .35f) },
            contentColor = SomnioTheme.colorScheme.onPrimary,
            shape = shape,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            contentPadding = contentPadding,
            content = content
        )
    }
}

@Composable
fun LowSurfaceButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(24.dp, 16.dp),
    shape: RoundedCornerShape = SomnioTheme.shapes.pretty,
    content: @Composable RowScope.() -> Unit
) {
    ProvideTextStyle(
        TextStyle(fontWeight = FontWeight.Medium, fontSize = 17.sp, textAlign = TextAlign.Center)
    ) {
        TemplateButton(
            modifier = modifier,
            onClick = onClick,
            enabled = enabled,
            defaultColor = SomnioTheme.colorScheme.lowSurface,
            pressedColor = SomnioTheme.colorScheme.run { lerp(lowSurface, primary, .065f) },
            contentColor = SomnioTheme.colorScheme.onSurface,
            shape = shape,
            horizontalArrangement = Arrangement.Center,
            borderStroke = BorderStroke(1.5.dp, SomnioTheme.colorScheme.border),
            contentPadding = contentPadding,
            content = content
        )
    }
}

@Composable
fun SurfaceButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(24.dp, 16.dp),
    shape: RoundedCornerShape = SomnioTheme.shapes.normal,
    content: @Composable RowScope.() -> Unit
) {
    ProvideTextStyle(
        TextStyle(fontWeight = FontWeight.Medium, fontSize = 17.sp)
    ) {
        TemplateButton(
            modifier = modifier,
            onClick = onClick,
            enabled = enabled,
            defaultColor = SomnioTheme.colorScheme.surface,
            pressedColor = SomnioTheme.colorScheme.run { lerp(surface, onSurface, .05f) },
            contentColor = SomnioTheme.colorScheme.onSurface,
            shape = shape,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            borderStroke = BorderStroke(1.dp, SomnioTheme.colorScheme.border),
            contentPadding = contentPadding,
            content = content
        )
    }
}

@Composable
fun ContainerButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(24.dp, 14.dp),
    shape: RoundedCornerShape = SomnioTheme.shapes.relativePretty,
    content: @Composable RowScope.() -> Unit
) {
    ProvideTextStyle(actionButtonTextStyle) {
        TemplateButton(
            modifier = modifier,
            enabled = enabled,
            onClick = onClick,
            defaultColor = SomnioTheme.colorScheme.highPrimary,
            pressedColor = SomnioTheme.colorScheme.run { lerp(highPrimary, onHighPrimary, .4f) },
            contentColor = SomnioTheme.colorScheme.onHighPrimary,
            shape = shape,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            //textStyle = actionButtonTextStyle,
            contentPadding = contentPadding,
            content = content
        )
    }
}

@Composable
fun IconContainerButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(16.dp, 8.dp),
    shape: RoundedCornerShape = SomnioTheme.shapes.relativePretty,
    content: @Composable RowScope.() -> Unit
) {
    TemplateButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        defaultColor = SomnioTheme.colorScheme.highPrimary,
        pressedColor = SomnioTheme.colorScheme.run { lerp(highPrimary, onHighPrimary, .25f) },
        contentColor = SomnioTheme.colorScheme.onHighPrimary,
        shape = shape,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = contentPadding,
        minSize = DpSize(Dp.Unspecified, 28.dp),
        content = content
    )
}

@Composable
fun LowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(24.dp, 14.dp),
    shape: RoundedCornerShape = SomnioTheme.shapes.relativePretty,
    content: @Composable RowScope.() -> Unit
) {
    ProvideTextStyle(actionButtonTextStyle) {
        TemplateButton(
            modifier = modifier,
            enabled = enabled,
            onClick = onClick,
            defaultColor = SomnioTheme.colorScheme.lowPrimary,
            pressedColor = SomnioTheme.colorScheme.run { lerp(lowPrimary, onLowPrimary, .2f) },
            contentColor = SomnioTheme.colorScheme.onLowPrimary,
            shape = shape,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            contentPadding = contentPadding,
            content = content
        )
    }
}

@Composable
fun IconLowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(16.dp, 8.dp),
    shape: RoundedCornerShape = SomnioTheme.shapes.relativePretty,
    content: @Composable RowScope.() -> Unit
) {
    TemplateButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        defaultColor = SomnioTheme.colorScheme.lowPrimary,
        pressedColor = SomnioTheme.colorScheme.run { lerp(highPrimary, onLowPrimary, .2f) },
        contentColor = SomnioTheme.colorScheme.onLowPrimary,
        shape = shape,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = contentPadding,
        minSize = DpSize(Dp.Unspecified, 28.dp),
        content = content
    )
}