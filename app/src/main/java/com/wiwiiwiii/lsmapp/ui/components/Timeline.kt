package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Timeline(total: Int) {

    Column(
        modifier = Modifier.width(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        repeat(total) { index ->

            //  CÍRCULO
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(Color.LightGray, CircleShape)
            )

            //  LÍNEA (menos el último nodo)
            if (index != total - 1) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(130.dp)
                        .background(Color.LightGray)
                )
            }
        }
    }
}