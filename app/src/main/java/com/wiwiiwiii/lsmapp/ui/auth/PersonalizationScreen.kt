package com.wiwiiwiii.lsmapp.ui.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.R
import com.wiwiiwiii.lsmapp.data.SessionManager
import com.wiwiiwiii.lsmapp.data.network.AuthApi
import com.wiwiiwiii.lsmapp.data.network.ProfileApi
import kotlinx.coroutines.launch

@Composable
fun PersonalizationScreen(
    navController: NavController,
    username: String
) {

    var navigateNext by rememberSaveable { mutableStateOf(false) }
    var selectedAvatar by remember { mutableStateOf<Int?>(null) }
    var loading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val session = SessionManager(context)
    val token = session.getToken()

    val avatars = listOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(36.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "Personalización",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "¡Es hora de darle vida a tu personaje!",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(30.dp))

        //  GRID DE AVATARES
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            avatars.chunked(2).forEach { row ->
                Row {
                    row.forEach { avatar ->

                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (selectedAvatar == avatar)
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    else
                                        MaterialTheme.colorScheme.surface
                                )
                                .clickable {
                                    selectedAvatar = avatar
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = avatar),
                                contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        val scope = rememberCoroutineScope()

        Button(
            onClick = {

                if (selectedAvatar == null || token == null) return@Button

                println("ANTES DE COROUTINE")

                scope.launch {

                    println("DENTRO DE COROUTINE")

                    try {
                        val userId = AuthApi().getUserId(token)
                        println("USER ID: $userId")

                        ProfileApi().createProfile(
                            token = token,
                            userId = userId,
                            username = username,
                            avatar = selectedAvatar.toString()
                        )

                        println("PROFILE")

                        withContext(Dispatchers.Main) {
                            println("NAVEGANDO")

                            navController.navigate("profile") {
                                popUpTo("welcome") { inclusive = true }
                                launchSingleTop = true
                            }
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .height(50.dp)
        ) {
            Text(if (loading) "Guardando..." else "Siguiente")
        }
    }
}