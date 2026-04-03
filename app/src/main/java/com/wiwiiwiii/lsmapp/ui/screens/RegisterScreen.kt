package com.wiwiiwiii.lsmapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.ui.theme.Background
import com.wiwiiwiii.lsmapp.ui.theme.ButtonGray
import com.wiwiiwiii.lsmapp.ui.theme.TopGray
import com.wiwiiwiii.lsmapp.ui.theme.TextGray
import com.wiwiiwiii.lsmapp.ui.theme.ContainerGray
import com.wiwiiwiii.lsmapp.R
import com.wiwiiwiii.lsmapp.ui.components.CustomInput

@Composable
fun RegisterScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {

        IconButton(onClick = { navController.popBackStack() }) {
            Text("←", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(
                    ContainerGray,
                    RoundedCornerShape(30.dp)
                )
                .padding(20.dp)
        ) {

            Column {

                Text("Registrarse", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Divider(
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                CustomInput(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email",
                    icon = R.drawable.mail
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

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        navController.navigate("personalization")
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonGray),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(180.dp)
                        .height(40.dp)
                ) {
                    Text("Registrarse", fontSize = 13.sp)
                }

                Spacer(modifier = Modifier.height(15.dp))

                Divider()

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("¿Ya tienes cuenta? ", fontSize = 11.sp)
                    Text("Inicia sesión", fontSize = 11.sp, color = TextGray)
                }
            }
        }
    }
}