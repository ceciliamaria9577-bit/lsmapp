package com.wiwiiwiii.lsmapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.wiwiiwiii.lsmapp.ui.components.AchievementItem
import com.wiwiiwiii.lsmapp.ui.components.AddFriendItem
import com.wiwiiwiii.lsmapp.ui.components.FriendItem
import com.wiwiiwiii.lsmapp.ui.components.ProfileHeader
import com.wiwiiwiii.lsmapp.ui.components.StatsBar
import com.wiwiiwiii.lsmapp.ui.components.TabsSection


@Composable
fun ProfileScreen() {

    var selectedTab by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // HEADER (parte superior)
        ProfileHeader()

        // CONTENEDOR PRINCIPAL (como tarjeta flotante)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 180.dp) // 👈 CLAVE: baja el contenido
                .background(
                    Color(0xFFF2F2F2),
                    RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // Nombre y usuario
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text("Nombre de Usuario", fontSize = 20.sp)
                Text("@usuario", color = Color.Gray)

                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }

            StatsBar()

            Spacer(modifier = Modifier.height(16.dp)) // debajo del StatsBar

            TabsSection(
                selectedTab = selectedTab,
                onTabSelected = { index -> selectedTab = index }
            )

            when (selectedTab) {
                0 -> LogrosSection()
                1 -> EstadisticasSection()
                2 -> AmigosSection()
            }
        }
    }
}

@Composable
fun LogrosSection() {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color(0xFFD9D9D9), RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {

        Text(
            "Personales",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = 2.dp
        )

        Row {
            repeat(4) {
                AchievementItem(unlocked = it < 2)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Tiempo",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = 2.dp
        )

        Row {
            repeat(4) {
                AchievementItem(unlocked = it < 2)
            }
        }
    }
}

@Composable
fun EstadisticasSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFD9D9D9), RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {

        // 🔹 GENERAL
        Text(
            "General",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = 2.dp
        )

        LinearProgressIndicator(
            progress = 0.3f,
            color = Color.DarkGray,
            trackColor = Color.Gray.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 CONOCIMIENTOS
        Text(
            "Conocimientos",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = 2.dp
        )

        Text("Abecedario")

        LinearProgressIndicator(
            progress = 0.7f,
            color = Color.DarkGray,
            trackColor = Color.Gray.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Números")

        LinearProgressIndicator(
            progress = 0.5f,
            color = Color.DarkGray,
            trackColor = Color.Gray.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
    }
}

@Composable
fun AmigosSection() {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color(0xFFD9D9D9), RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {

        Text(
            "Amigos",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = 2.dp
        )

        Row {
            repeat(3) { FriendItem() }
            AddFriendItem()
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Solicitudes de amistad",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = 2.dp
        )

        Text(
            "Aún no hay solicitudes",
            color = Color.Gray
        )
    }
}