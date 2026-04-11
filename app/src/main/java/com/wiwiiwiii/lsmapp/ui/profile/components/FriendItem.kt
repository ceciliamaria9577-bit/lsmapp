package com.wiwiiwiii.lsmapp.ui.profile.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import com.wiwiiwiii.lsmapp.ui.components.SquareItem
import com.wiwiiwiii.lsmapp.ui.theme.LocalExtendedColors

@Composable
fun FriendItem() {

    SquareItem(
        color = LocalExtendedColors.current.miniContainer
    )
}

@Composable
fun AddFriendItem() {

    SquareItem(
        color = LocalExtendedColors.current.miniContainerDisabled
    ) {
        Text("+", fontSize = 24.sp)
    }
}