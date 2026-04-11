package com.wiwiiwiii.lsmapp.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import com.wiwiiwiii.lsmapp.R

val InstrumentSans = FontFamily(
    Font(R.font.instrument_sans_regular, FontWeight.Normal),
    Font(R.font.instrument_sans_medium, FontWeight.Medium),
    Font(R.font.instrument_sans_semibold, FontWeight.SemiBold),
    Font(R.font.instrument_sans_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(fontFamily = InstrumentSans),
    titleLarge = TextStyle(fontFamily = InstrumentSans),
    labelLarge = TextStyle(fontFamily = InstrumentSans)
)