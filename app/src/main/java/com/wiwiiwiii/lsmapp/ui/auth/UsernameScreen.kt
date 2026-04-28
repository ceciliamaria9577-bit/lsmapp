package com.wiwiiwiii.lsmapp.ui.auth

import com.wiwiiwiii.lsmapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.ui.components.CustomInput

@Composable
fun UsernameScreen(navController: NavController) {

    var username by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Elige tu nombre de usuario",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomInput(
            value = username,
            onValueChange = { username = it },
            placeholder = "Nombre de usuario",
            icon = R.drawable.ic_profile
        )

        if (error.isNotEmpty()) {
            Text(error, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (username.length < 3) {
                    error = "Muy corto"
                } else {
                    navController.navigate("avatar/$username")
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Siguiente")
        }
    }
}