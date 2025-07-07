package com.somnionocte.somnio_design.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.somnionocte.atomic_components.components.AtomicTextField
import com.somnionocte.somnio_design.LocalContentColor
import com.somnionocte.somnio_design.SomnioTheme
import com.somnionocte.somnio_design.snpro

val textStyleTextField = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Medium, fontFamily = snpro)

@Composable
fun TextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = textStyleTextField,
    label: @Composable() (() -> Unit)? = null,
    placeholder: @Composable() (() -> Unit)? = null,
    prefix: @Composable() (() -> Unit)? = null,
    suffix: @Composable() (() -> Unit)? = null,
    support: @Composable() (() -> Unit)? = null,
    backgroundColor: Color = SomnioTheme.colorScheme.run { lerp(surfaceContainer, lowPrimary, .15f) },
    shape: RoundedCornerShape = SomnioTheme.shapes.pretty,
    padding: PaddingValues = PaddingValues(22.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    scrollState: ScrollState = rememberScrollState(),
    interactionSource: MutableInteractionSource? = null,
    minWidth: Dp = 160.dp,
    maxWidth: Dp = Dp.Unspecified,
    innerTextFieldGap: Dp = 16.dp,
) {
    AtomicTextField(
        state = state,
        designModifier = modifier.background(backgroundColor, shape).padding(padding),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle.merge(TextStyle(color = LocalContentColor.current)),
        label = label,
        placeholder = placeholder,
        prefix = prefix,
        suffix = suffix,
        support = support,
        interactionSource = interactionSource,
        textColor = LocalContentColor.current,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        lineLimits = lineLimits,
        inputTransformation = inputTransformation,
        outputTransformation = outputTransformation,
        scrollState = scrollState,
        minWidth = minWidth,
        maxWidth = maxWidth,
        innerTextFieldGap = innerTextFieldGap,
    )
}

@Composable
fun TextField(
    value: String,
    onChange: (value: String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = textStyleTextField,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable() () -> Unit)? = null,
    prefix: (@Composable () -> Unit)? = null,
    suffix: (@Composable () -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    backgroundColor: Color = SomnioTheme.colorScheme.run { lerp(surfaceContainer, lowPrimary, .15f) },
    shape: RoundedCornerShape = SomnioTheme.shapes.run { if(singleLine) relativePretty else pretty },
    padding: PaddingValues = PaddingValues(22.dp),
    interactionSource: MutableInteractionSource? = null
) {
    AtomicTextField(
        value = value,
        onValueChange = onChange,
        designModifier = modifier.background(backgroundColor, shape).padding(padding),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle.merge(TextStyle(color = LocalContentColor.current)),
        label = label,
        placeholder = placeholder,
        prefix = prefix,
        suffix = suffix,
        support = supportingText,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        innerTextFieldGap = 16.dp,
        cursorColor = SomnioTheme.colorScheme.primary,
    )
}