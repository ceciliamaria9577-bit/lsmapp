package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn

@Composable
fun FullWidthDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = (-16).dp)
            .widthIn(min = 0.dp)
            .fillMaxWidth()
            .padding(end = 16.dp),
        thickness = 3.dp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}