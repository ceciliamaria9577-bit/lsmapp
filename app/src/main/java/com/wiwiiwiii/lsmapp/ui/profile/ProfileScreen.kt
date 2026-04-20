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
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.ui.components.AchievementItem
import com.wiwiiwiii.lsmapp.ui.components.CustomDivider
import com.wiwiiwiii.lsmapp.ui.components.FullDivider
import com.wiwiiwiii.lsmapp.ui.components.InsetDivider
import com.wiwiiwiii.lsmapp.ui.profile.components.AddFriendItem
import com.wiwiiwiii.lsmapp.ui.profile.components.FriendItem
import com.wiwiiwiii.lsmapp.ui.profile.components.ProfileHeader
import com.wiwiiwiii.lsmapp.ui.profile.components.StatsBar
import com.wiwiiwiii.lsmapp.ui.profile.components.TabsSection
import com.wiwiiwiii.lsmapp.ui.theme.LocalExtendedColors
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel

@Composable
fun ProfileScreen(
    progressViewModel: ProgressViewModel,
    navController: NavController) {

    var selectedTab by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // HEADER (parte superior)
        ProfileHeader(
            onSettingsClick = {
                navController.navigate("settings")
            }
        )

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
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(6.dp))
                Text("@usuario",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(6.dp))

                CustomDivider(paddingVertical = 16.dp)
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
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        CustomDivider(paddingVertical = 12.dp)

        Row {
            repeat(4) {
                AchievementItem(unlocked = it < 2)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Tiempo",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        CustomDivider(paddingVertical = 12.dp)

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
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        CustomDivider(paddingVertical = 12.dp)

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
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Divider(
            modifier = Modifier.padding(vertical = 12.dp)
                .height(3.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            thickness = 2.dp
        )

        Text("Abecedario",
            style = MaterialTheme.typography.bodyLarge,
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
            style = MaterialTheme.typography.bodyLarge,
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
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        CustomDivider(paddingVertical = 12.dp)

        Row {
            repeat(3) { FriendItem() }
            AddFriendItem()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Solicitudes de amistad",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        CustomDivider(paddingVertical = 12.dp)

        Text(
            "Aún no hay solicitudes",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            )
    }
}