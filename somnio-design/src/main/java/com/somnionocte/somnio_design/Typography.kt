package com.somnionocte.somnio_design

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class Typography(
    val body: TextStyle = TextStyle(fontSize = 16.sp, fontFamily = snpro),
    val title: TextStyle = TextStyle(fontSize = 28.sp, fontFamily = snpro, fontWeight = FontWeight.SemiBold),
)

val snpro = FontFamily(
    Font(R.font.snpro_regular),
    Font(R.font.snpro_semibold, FontWeight.SemiBold),
    Font(R.font.snpro_light, FontWeight.Light),
    Font(R.font.snpro_medium, FontWeight.Medium),
)

val cormorant_garamond = FontFamily(
    Font(R.font.cormorant_garamond),
    Font(R.font.cormorant_garamond_italic, style = FontStyle.Italic),
)

val LocalTypography = staticCompositionLocalOf { Typography() }