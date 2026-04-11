package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.wiwiiwiii.lsmapp.ui.theme.LocalExtendedColors

@Composable
fun AchievementItem(unlocked: Boolean) {

    val extra = LocalExtendedColors.current

    SquareItem(
        color = if (unlocked) extra.miniContainer
        else extra.miniContainerDisabled
    )
}