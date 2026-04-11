package com.wiwiiwiii.lsmapp.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ExtendedColors(
    val miniContainer: Color,
    val miniContainerDisabled: Color,
    val buttonText: Color
    )

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        miniContainer = Color.Unspecified,
        miniContainerDisabled = Color.Unspecified,
        buttonText = Color.Unspecified
    )
}