package com.wiwiiwiii.lsmapp.ui.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.wiwiiwiii.lsmapp.ui.components.AchievementItem
import com.wiwiiwiii.lsmapp.ui.profile.components.AddFriendItem
import com.wiwiiwiii.lsmapp.ui.profile.components.FriendItem
import com.wiwiiwiii.lsmapp.ui.profile.components.ProfileHeader
import com.wiwiiwiii.lsmapp.ui.profile.components.StatsBar
import com.wiwiiwiii.lsmapp.ui.profile.components.TabsSection
import com.wiwiiwiii.lsmapp.ui.theme.LocalExtendedColors
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel

@Composable
fun ProfileScreen(progressViewModel: ProgressViewModel) {

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
                .padding(top = 180.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // Nombre y usuario
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text("Nombre de Usuario",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(6.dp))
                Text("@usuario",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(6.dp))

                Divider(modifier = Modifier.padding(vertical = 20.dp)
                    .height(3.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            StatsBar(progressViewModel)

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
            .background(
                color = MaterialTheme.colorScheme.surface,
                RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {

        Text(
            "Personales",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp)
                .height(3.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp)
                .height(3.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
            .background(
                color = MaterialTheme.colorScheme.surface,
                RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {

        //  GENERAL
        Text(
            "General",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp)
                .height(3.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            thickness = 2.dp
        )

        LinearProgressIndicator(
            progress = 0.3f,
            color = MaterialTheme.colorScheme.primary,
            trackColor = LocalExtendedColors.current.miniContainer.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //  CONOCIMIENTOS
        Text(
            "Conocimientos",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp)
                .height(3.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            thickness = 2.dp
        )

        Text("Abecedario",
            fontSize = 14.sp,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        LinearProgressIndicator(
            progress = 0.7f,
            color = MaterialTheme.colorScheme.primary,
            trackColor = LocalExtendedColors.current.miniContainer.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp)
        )

        Text("Números",
            fontSize = 14.sp,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        LinearProgressIndicator(
            progress = 0.5f,
            color = MaterialTheme.colorScheme.primary,
            trackColor = LocalExtendedColors.current.miniContainer.copy(alpha = 0.3f),
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
            .background(
                color = MaterialTheme.colorScheme.surface,
                RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {

        Text(
            "Amigos",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp)
                .height(3.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            thickness = 2.dp
        )

        Row {
            repeat(3) { FriendItem() }
            AddFriendItem()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Solicitudes de amistad",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp)
                .height(3.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            thickness = 2.dp
        )

        Text(
            "Aún no hay solicitudes",
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}