package com.wiwiiwiii.lsmapp.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SocialBox(icon: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = MaterialTheme.colorScheme.surface
                , RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(icon, fontSize = 18.sp)
    }
}