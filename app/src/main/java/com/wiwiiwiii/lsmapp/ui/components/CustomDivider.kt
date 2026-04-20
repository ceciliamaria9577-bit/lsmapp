package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

@Composable
fun CustomDivider(
    paddingHorizontal: Dp = 0.dp,
    paddingVertical: Dp = 0.dp
) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = paddingHorizontal,
                vertical = paddingVertical
            ),
        thickness = 3.dp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}