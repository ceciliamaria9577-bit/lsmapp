package com.wiwiiwiii.lsmapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.R
import com.wiwiiwiii.lsmapp.ui.theme.Background
import com.wiwiiwiii.lsmapp.ui.theme.ButtonGray
import com.wiwiiwiii.lsmapp.ui.theme.TopGray
import com.wiwiiwiii.lsmapp.ui.theme.TextGray
import com.wiwiiwiii.lsmapp.ui.theme.ContainerGray
import com.wiwiiwiii.lsmapp.ui.components.CustomInput

@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {

        // 🔙 Flecha
        IconButton(onClick = { navController.popBackStack() }) {
            Text("←", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🧱 CONTENEDOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(
                    ContainerGray,
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(20.dp)
        ) {

            Column {

                Text(
                    "Iniciar Sesión",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Divider(
                    modifier = Modifier
                        .padding(vertical = 6.dp),
                )

                Spacer(modifier = Modifier.height(10.dp))

                CustomInput(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email",
                    icon = com.wiwiiwiii.lsmapp.R.drawable.mail
                )

                Spacer(modifier = Modifier.height(10.dp))

                CustomInput(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Contraseña",
                    icon = R.drawable.lock
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    "Olvidé la contraseña",
                    fontSize = 11.sp,
                    modifier = Modifier.align(Alignment.End),
                    color = TextGray
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        navController.navigate("home") {
                            popUpTo("welcome") { inclusive = true }
                        }
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonGray),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(180.dp)
                        .height(40.dp)
                ) {
                    Text("Iniciar sesión", fontSize = 13.sp)
                }

                Spacer(modifier = Modifier.height(15.dp))

                // ── línea texto línea
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Divider(modifier = Modifier.weight(1f))
                    Text("  Iniciar sesión con  ", fontSize = 11.sp)
                    Divider(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SocialIcon("f")
                    Spacer(modifier = Modifier.width(10.dp))
                    SocialIcon("G")
                }

                Spacer(modifier = Modifier.height(15.dp))

                Divider()

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("¿No tienes cuenta? ", fontSize = 11.sp)
                    Text("Regístrate", fontSize = 11.sp, color = TextGray)
                }
            }
        }
    }
}

@Composable
fun SocialIcon(text: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.White, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 18.sp)
    }
}