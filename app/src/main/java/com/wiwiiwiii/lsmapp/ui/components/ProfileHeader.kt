package com.wiwiiwiii.lsmapp.ui.components
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileHeader() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.LightGray)
    ) {

        // Botones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(Icons.Default.Close, contentDescription = null)
            Icon(Icons.Default.Settings, contentDescription = null)
        }

        // 👤 Avatar (placeholder)
        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 50.dp) // 👈 sobresale hacia abajo
                .background(Color.Transparent, CircleShape)
        )
    }
}