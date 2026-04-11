package com.wiwiiwiii.lsmapp.ui.library.alphabet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController

@Composable
fun AlphabetScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {

        //  Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {
                navController.navigate("bookmark") {
                    popUpTo("bookmark") { inclusive = false }
                }
            })
            {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary)
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Abecedario",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.offset(x = (-10).dp)
                )
            }
        }

        //  Grid de letras
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(alphabetList) { index, item ->
                AlphabetCard(
                    item = item,
                    onClick = {
                        navController.navigate("letter_detail/$index")
                    }
                )
            }
        }
    }
}