package com.wiwiiwiii.lsmapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp

@Composable
fun Timeline(
    totalItems: Int,
    completedCount: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        repeat(totalItems) { index ->

            val isTemaNode = index == 0

            val isCompleted = when {
                isTemaNode -> true //
                else -> index <= completedCount
            }

            val isLineCompleted = when {
                isTemaNode -> completedCount > 0
                else -> index < completedCount
            }

            //  CÍRCULO
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        if (isCompleted)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outline,
                        CircleShape
                    )
            )

            //  LÍNEA
            if (index != totalItems - 1) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(120.dp)
                        .background(
                            if (isLineCompleted)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.outline
                        )
                )
            }
        }
    }
}