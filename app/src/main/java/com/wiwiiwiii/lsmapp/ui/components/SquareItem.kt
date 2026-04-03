package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SquareItem(
    color: Color,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .padding(4.dp)
            .background(color, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center,
        content = content
    )
}