package com.wiwiiwiii.lsmapp.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.R
import com.wiwiiwiii.lsmapp.ui.components.CustomInput
import com.wiwiiwiii.lsmapp.ui.theme.LocalExtendedColors


@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        //  Flecha
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(36.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        //  CONTENEDOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(20.dp)
        ) {

            Column {

                Text(
                    "Iniciar Sesión",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(12.dp)
                )

                Divider(
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .height(3.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(28.dp))

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
                    icon = R.drawable.lock,
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    "Olvidé la contraseña",
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.End)
                        .padding(18.dp),
                    color = MaterialTheme.colorScheme.primary,
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        navController.navigate("home") {
                            popUpTo("welcome") { inclusive = true }
                        }
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                        .width(200.dp)
                        .height(40.dp)
                ) {
                    Text("Iniciar sesión",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()  ,
                        color = LocalExtendedColors.current.buttonText
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                //  Línea texto línea
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)) {
                    Divider(modifier = Modifier.weight(1f)
                        .height(3.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("  Iniciar sesión con  ",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Divider(modifier = Modifier.weight(1f)
                        .height(3.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(

                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp)
                ) {
                    SocialIcon(R.drawable.facebook)
                    Spacer(modifier = Modifier.width(10.dp))
                    SocialIcon(R.drawable.google)
                }

                Spacer(modifier = Modifier.height(15.dp))

                Divider(modifier = Modifier.height(3.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("¿No tienes cuenta? ",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary)
                    Text("Regístrate",
                        fontSize = 14.sp,
                        color = LocalExtendedColors.current.buttonText
                    )
                }
            }
        }
    }
}

@Composable
fun SocialIcon(iconRes: Int) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = LocalExtendedColors.current.buttonText)
            .clickable { }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}