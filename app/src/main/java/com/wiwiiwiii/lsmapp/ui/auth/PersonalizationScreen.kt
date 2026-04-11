package com.wiwiiwiii.lsmapp.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.ui.theme.LocalExtendedColors

@Composable
fun PersonalizationScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
            .padding(24.dp)
    ) {

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(36.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text("Personalización",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold)

        Text("¡Es hora de darle vida a tu personaje!",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp)

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            repeat(2) {
                Row {
                    repeat(2) {
                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .padding(10.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    RoundedCornerShape(12.dp)
                                )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("home") },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onSurfaceVariant),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .height(50.dp)
        ) {
            Text("Siguiente",
                color = LocalExtendedColors.current.buttonText,
                fontSize = 18.sp)
        }
    }
}