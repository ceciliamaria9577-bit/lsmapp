package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun AchievementItem(unlocked: Boolean) {

    SquareItem(
        color = if (unlocked) Color.Gray else Color.LightGray
    )
}