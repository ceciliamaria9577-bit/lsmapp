package com.wiwiiwiii.lsmapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimelineItem(
    isCompleted: Boolean,
    isLineCompleted: Boolean,
    isLast: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        //  PUNTO
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(
                    if (isCompleted)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surface,
                    CircleShape
                )
        )

        //  LÍNEA
        if (!isLast) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(110.dp)
                    .background(
                        if (isLineCompleted)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surface
                    )
            )
        }
    }
}