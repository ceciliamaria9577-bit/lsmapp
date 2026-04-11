package com.wiwiiwiii.lsmapp.ui.library


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiwiiwiii.lsmapp.ui.library.components.LibraryCard
import com.wiwiiwiii.lsmapp.ui.library.data.libraryItems
import androidx.navigation.NavController

@Composable
fun LibraryScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Biblioteca",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(libraryItems) { item ->
                LibraryCard(
                    item = item,
                    onClick = {
                        if (item.title == "Abecedario") {
                            navController.navigate("alphabet")
                        }
                    }
                )
            }
        }
    }
}