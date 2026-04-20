package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun FullDivider() {
    androidx.compose.material3.Divider(
        color = MaterialTheme.colorScheme.onSurface,
        thickness = 3.dp
    )
}