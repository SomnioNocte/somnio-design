package com.somnionocte.somnio_design

import android.content.Context
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DoNotInline
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.util.lerp
import com.github.ajalt.colormath.extensions.android.composecolor.toColormathColor
import com.github.ajalt.colormath.extensions.android.composecolor.toComposeColor
import com.github.ajalt.colormath.model.Oklch

@Immutable
data class ColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val highPrimary: Color,
    val onHighPrimary: Color,
    val lowPrimary: Color,
    val onLowPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val lowSurface: Color,
    val onSurface: Color,
    val surfaceContainer: Color,
    val onSurfaceContainer: Color,
    val border: Color,
    val primaryHue: Float,
    val getAccentColor: (hue: Float) -> Color = { Color.Unspecified },
    val getOnAccentColor: (hue: Float) -> Color = { Color.Unspecified },
    val getHighAccentColor: (hue: Float) -> Color = { Color.Unspecified },
    val getOnHighAccentColor: (hue: Float) -> Color = { Color.Unspecified },
    val getLowAccentColor: (hue: Float) -> Color = { Color.Unspecified },
    val getOnLowAccentColor: (hue: Float) -> Color = { Color.Unspecified },
) {
    companion object {
        fun generateLightTheme(
            primaryHue: Float = 148f, //148f 287f

            accentLightness: Float = .53f,
            accentChroma: Float = .088f,
            onAccentLightness: Float = 1f,
            onAccentChroma: Float = 0f,

            highAccentLightness: Float = .86f,
            highAccentChroma: Float = .092f,
            onHighAccentLightness: Float = .25f,
            onHighAccentChroma: Float = .05f,

            lowAccentLightness: Float = .93f,
            lowAccentChroma: Float = .056f,
            onLowAccentLightness: Float = .3f,
            onLowAccentChroma: Float = .056f,

            backgroundHue: Float = primaryHue,
            backgroundLightness: Float = .96f,
            backgroundChroma: Float = .02f,

            surfaceLightness: Float = .98f,
            surfaceChroma: Float = .008f,
            surfaceContainerLightness: Float = .94f,
            surfaceContainerChroma: Float = .028f,
            lowSurfaceLightness: Float = .96870005f,
            lowSurfaceChroma: Float = .01478f,

            onSurfaceContainerLightness: Float = .15f,
            onSurfaceContainerChroma: Float = .015f,

            borderLightness: Float = .935f,
            borderChroma: Float = .056f,
        ): ColorScheme {
            val getAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(accentLightness, 0f, 0f).toComposeColor()
                else Oklch(accentLightness, accentChroma, hue).toComposeColor()
            }

            val getOnAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(onAccentLightness, 0f, 0f).toComposeColor()
                else Oklch(onAccentLightness, onAccentChroma, hue).toComposeColor()
            }

            val getLowAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(lowAccentLightness, 0f, 0f).toComposeColor()
                else Oklch(lowAccentLightness, lowAccentChroma, hue).toComposeColor()
            }

            val getOnLowAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(onLowAccentLightness, 0f, 0f).toComposeColor()
                else Oklch(onLowAccentLightness, onLowAccentChroma, hue).toComposeColor()
            }

            val getHighAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(highAccentLightness, 0f, 0f).toComposeColor()
                else Oklch(highAccentLightness, highAccentChroma, hue).toComposeColor()
            }

            val getOnHighAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(onHighAccentLightness, 0f, 0f).toComposeColor()
                else Oklch(onHighAccentLightness, onHighAccentChroma, hue).toComposeColor()
            }

            return ColorScheme(
                primaryHue = primaryHue,
                primary = getAccentColor(primaryHue),
                onPrimary = getOnAccentColor(primaryHue),
                lowPrimary = getLowAccentColor(primaryHue),
                onLowPrimary = getOnLowAccentColor(primaryHue),
                highPrimary = getHighAccentColor(primaryHue),
                onHighPrimary = getOnHighAccentColor(primaryHue),
                background =
                    if(backgroundHue.isNaN()) Oklch(backgroundLightness, 0f, 0f).toComposeColor()
                    else Oklch(backgroundLightness, backgroundChroma, backgroundHue).toComposeColor(),
                onBackground = Color.Black,
                surface =
                    if(backgroundHue.isNaN()) Oklch(surfaceLightness, 0f, 0f).toComposeColor()
                    else Oklch(surfaceLightness, surfaceChroma, backgroundHue).toComposeColor(),
                onSurface = Color.Black,
                surfaceContainer =
                    if(backgroundHue.isNaN()) Oklch(surfaceContainerLightness, 0f, 0f).toComposeColor()
                    else Oklch(surfaceContainerLightness, surfaceContainerChroma, backgroundHue).toComposeColor(),
                onSurfaceContainer =
                    if(backgroundHue.isNaN()) Oklch(onSurfaceContainerLightness, 0f, 0f).toComposeColor()
                    else Oklch(onSurfaceContainerLightness, onSurfaceContainerChroma, backgroundHue).toComposeColor(),
                getAccentColor = getAccentColor,
                getOnAccentColor = getOnAccentColor,
                getLowAccentColor = getLowAccentColor,
                getOnLowAccentColor = getOnLowAccentColor,
                getHighAccentColor = getHighAccentColor,
                getOnHighAccentColor = getOnHighAccentColor,
                border =
                    if(backgroundHue.isNaN()) Oklch(borderLightness, 0f, 0f).toComposeColor()
                    else Oklch(borderLightness, borderChroma, primaryHue).toComposeColor(),
                lowSurface =
                    if(backgroundHue.isNaN()) Oklch(lowSurfaceLightness, 0f, 0f).toComposeColor()
                    else Oklch(lowSurfaceLightness, lowSurfaceChroma, backgroundHue).toComposeColor()
            )
        }

        fun generateDarkTheme(
            primaryHue: Float = 148f,

            accentLightness: Float = .82f,
            accentChroma: Float = .104f,
            onAccentLightness: Float = .31f,
            onAccentChroma: Float = .064f,

            highAccentLightness: Float = .91f,
            highAccentChroma: Float = .07f,
            onHighAccentLightness: Float = .35f,
            onHighAccentChroma: Float = .07f,

            lowAccentLightness: Float = .39f,
            lowAccentChroma: Float = .084f,
            onLowAccentLightness: Float = .9f,
            onLowAccentChroma: Float = .104f,

            backgroundHue: Float = primaryHue,
            backgroundLightness: Float = .14f,
            backgroundChroma: Float = .016f,

            surfaceLightness: Float = .20f,
            surfaceChroma: Float = .024f,
            surfaceContainerLightness: Float = .17f,
            surfaceContainerChroma: Float = .012f,
            lowSurfaceLightness: Float = .1661f,
            lowSurfaceChroma: Float = .019480001f,

            onSurfaceContainerLightness: Float = .9f,
            onSurfaceContainerChroma: Float = .015f,
            borderLightness: Float = .235f,
            borderChroma: Float = .03f,
        ): ColorScheme {
            val getAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(accentLightness, 0f, 0f).toComposeColor()
                else Oklch(accentLightness, accentChroma, hue).toComposeColor()
            }

            val getOnAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(onAccentLightness, 0f, 0f).toComposeColor()
                else Oklch(onAccentLightness, onAccentChroma, hue).toComposeColor()
            }

            val getLowAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(lowAccentLightness, 0f, 0f).toComposeColor()
                else Oklch(lowAccentLightness, lowAccentChroma, hue).toComposeColor()
            }

            val getOnLowAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(onLowAccentLightness, 0f, 0f).toComposeColor()
                else Oklch(onLowAccentLightness, onLowAccentChroma, hue).toComposeColor()
            }

            val getHighAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(highAccentLightness, 0f, 0f).toComposeColor()
                else Oklch(highAccentLightness, highAccentChroma, hue).toComposeColor()
            }

            val getOnHighAccentColor = { hue: Float ->
                if(hue.isNaN()) Oklch(onHighAccentLightness, 0f, 0f).toComposeColor()
                else Oklch(onHighAccentLightness, onHighAccentChroma, hue).toComposeColor()
            }

            return ColorScheme(
                primaryHue = primaryHue,
                primary = getAccentColor(primaryHue),
                onPrimary = getOnAccentColor(primaryHue),
                lowPrimary = getLowAccentColor(primaryHue),
                onLowPrimary = getOnLowAccentColor(primaryHue),
                highPrimary = getHighAccentColor(primaryHue),
                onHighPrimary = getOnHighAccentColor(primaryHue),
                background =
                    if(backgroundHue.isNaN()) Oklch(backgroundLightness, 0f, 0f).toComposeColor()
                    else Oklch(backgroundLightness, backgroundChroma, backgroundHue).toComposeColor(),
                onBackground = Color.White,
                surface =
                    if(backgroundHue.isNaN()) Oklch(surfaceLightness, 0f, 0f).toComposeColor()
                    else Oklch(surfaceLightness, surfaceChroma, backgroundHue).toComposeColor(),
                onSurface = Color.White,
                surfaceContainer =
                    if(backgroundHue.isNaN()) Oklch(surfaceContainerLightness, 0f, 0f).toComposeColor()
                    else Oklch(surfaceContainerLightness, surfaceContainerChroma, backgroundHue).toComposeColor(),
                onSurfaceContainer =
                    if(backgroundHue.isNaN()) Oklch(onSurfaceContainerLightness, 0f, 0f).toComposeColor()
                    else Oklch(onSurfaceContainerLightness, onSurfaceContainerChroma, backgroundHue).toComposeColor(),
                getAccentColor = getAccentColor,
                getOnAccentColor = getOnAccentColor,
                getLowAccentColor = getLowAccentColor,
                getOnLowAccentColor = getOnLowAccentColor,
                getHighAccentColor = getHighAccentColor,
                getOnHighAccentColor = getOnHighAccentColor,
                border =
                    if(backgroundHue.isNaN()) Oklch(borderLightness, 0f, 0f).toComposeColor()
                    else Oklch(borderLightness, borderChroma, primaryHue).toComposeColor(),
                lowSurface =
                    if(backgroundHue.isNaN()) Oklch(lowSurfaceLightness, 0f, 0f).toComposeColor()
                    else Oklch(lowSurfaceLightness, lowSurfaceChroma, backgroundHue).toComposeColor()
            )
        }
    }
}

val LocalColorScheme = staticCompositionLocalOf {
    ColorScheme(
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        0f
    )
}

private object ColorResourceHelper {
    @DoNotInline
    fun getColor(context: Context, @ColorRes id: Int): Color {
        return Color(context.resources.getColor(id, context.theme))
    }
}

private fun getAccentSystemColorHueOrDefault(context: Context, default: Float = 270f): Float {
    val systemAccentColor =
        if (Build.VERSION.SDK_INT >= 34)
            ColorResourceHelper.getColor(context, android.R.color.system_primary_dark)
        else if (Build.VERSION.SDK_INT >= 31)
            ColorResourceHelper.getColor(context, android.R.color.system_accent1_200)
        else
            null

    println(systemAccentColor)

    return systemAccentColor?.run { toColormathColor().toOklch().h } ?: default
}

@Composable
fun WithColorScheme(
    hue: Float? = null,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorScheme provides rememberColorScheme(hue),
        content = content
    )
}

@Composable
fun rememberColorScheme(
    hue: Float? = null
): ColorScheme {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    return remember {
        val accentHue = hue ?: getAccentSystemColorHueOrDefault(context, 270f)

        if(isDarkTheme) ColorScheme.generateDarkTheme(primaryHue = accentHue)
        else ColorScheme.generateLightTheme(primaryHue = accentHue)
    }
}