package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun FriendItem() {

    SquareItem(
        color = Color.Gray
    )
}

@Composable
fun AddFriendItem() {

    SquareItem(
        color = Color.LightGray
    ) {
        Text("+", fontSize = 24.sp)
    }
}