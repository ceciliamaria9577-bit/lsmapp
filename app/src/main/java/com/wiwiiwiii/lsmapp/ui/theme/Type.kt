package com.wiwiiwiii.lsmapp.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.wiwiiwiii.lsmapp.R

val InstrumentSans = FontFamily(
    Font(R.font.instrument_sans_regular, FontWeight.Normal),
    Font(R.font.instrument_sans_medium, FontWeight.Medium),
    Font(R.font.instrument_sans_semibold, FontWeight.SemiBold),
    Font(R.font.instrument_sans_bold, FontWeight.Bold)
)

val ExtraSmallText = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal
)

val Typography = Typography(
    //  EXTRA GRANDE (casos especiales)
    displayLarge = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    ),

    //  TÍTULOS PRINCIPALES
    headlineMedium = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),

    //  TÍTULOS (secciones)
    titleLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),

    //  SUBTÍTULO (bold)
    titleMedium = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    ),

    //  SUBTÍTULO (semi)
    titleSmall = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal
    ),

    //  SUBTÍTULO NORMAL
    labelLarge = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    ),

    //  SUBTÍTULO MÁS PEQUEÑO (bold)
    labelMedium = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold
    ),

    //  SUBTÍTULO MÁS PEQUEÑO (semi)
    labelSmall = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal
    ),

    //  TEXTO NORMAL (grande)
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    ),

    //  TEXTO NORMAL (semi)
    bodyMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),

    //  TEXTO NORMAL (bold)
    bodySmall = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )

)

