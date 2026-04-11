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
import com.wiwiiwiii.lsmapp.R
import com.wiwiiwiii.lsmapp.ui.components.CustomInput
import com.wiwiiwiii.lsmapp.ui.theme.LocalExtendedColors


@Composable
fun RegisterScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(36.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(30.dp)
                )
                .padding(20.dp)
        ) {

            Column {

                Text("Registrarse",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(12.dp))

                Divider(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .height(3.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Spacer(modifier = Modifier.height(28.dp))

                CustomInput(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email",
                    icon = R.drawable.mail,
                    )

                Spacer(modifier = Modifier.height(10.dp))

                CustomInput(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Contraseña",
                    icon = R.drawable.lock
                )

                Spacer(modifier = Modifier.height(10.dp))

                CustomInput(
                    value = confirm,
                    onValueChange = { confirm = it },
                    placeholder = "Confirmar contraseña",
                    icon = R.drawable.lock
                )

                Spacer(modifier = Modifier.height(10.dp))

                CustomInput(
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = "Teléfono",
                    icon = R.drawable.phone
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {
                        navController.navigate("personalization")
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.CenterHorizontally)
                        .width(200.dp)
                        .height(40.dp)
                ) {
                    Text("Registrarse",
                        fontSize = 20.sp,
                        color = LocalExtendedColors.current.buttonText
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Divider(modifier = Modifier.height(3.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("¿Ya tienes cuenta? ",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text("Inicia sesión",
                        fontSize = 14.sp,
                        color = LocalExtendedColors.current.buttonText
                    )
                }
            }
        }
    }
}