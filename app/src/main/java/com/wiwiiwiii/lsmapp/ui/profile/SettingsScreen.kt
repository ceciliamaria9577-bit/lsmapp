package com.wiwiiwiii.lsmapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.ui.profile.components.AccountCardItem
import com.wiwiiwiii.lsmapp.ui.profile.components.CardContainer
import com.wiwiiwiii.lsmapp.ui.profile.components.SectionTitle
import com.wiwiiwiii.lsmapp.ui.profile.components.SettingsHeader
import com.wiwiiwiii.lsmapp.ui.profile.components.ThemeItem
import com.wiwiiwiii.lsmapp.ui.profile.components.ToggleItem
import com.wiwiiwiii.lsmapp.ui.profile.components.ExpandableItem
import com.wiwiiwiii.lsmapp.ui.profile.components.ToggleCardItem

@Composable
fun SettingsScreen(navController: NavController) {

    var sonidoExpanded by remember { mutableStateOf(false) }
    var temasExpanded by remember { mutableStateOf(false) }

    var efectosSonido by remember { mutableStateOf(false) }
    var vibracion by remember { mutableStateOf(false) }
    var notificaciones by remember { mutableStateOf(false) }

    var temaSeleccionado by remember { mutableStateOf("Claro") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        //  HEADER
        SettingsHeader(navController)

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            //  GENERAL
            SectionTitle("General")

            CardContainer {

                // GENERAL

                ExpandableItem(
                    title = "Sonido y vibracion",
                    expanded = sonidoExpanded,
                    onClick = { sonidoExpanded = !sonidoExpanded }
                ) {
                    ToggleItem("Efectos de sonido", efectosSonido) { efectosSonido = it }
                    ToggleItem("Vibracion", vibracion) { vibracion = it }
                }

                ExpandableItem(
                    title = "Temas",
                    expanded = temasExpanded,
                    onClick = { temasExpanded = !temasExpanded }
                ) {
                    ThemeItem("Claro", temaSeleccionado) { temaSeleccionado = "Claro" }
                    ThemeItem("Oscuro", temaSeleccionado) { temaSeleccionado = "Oscuro" }
                    ThemeItem("Ambos", temaSeleccionado) { temaSeleccionado = "Ambos" }
                }

                ToggleCardItem("Notificaciones", notificaciones) {
                    notificaciones = it
                }

                // CUENTA
                SectionTitle("Cuenta")

                AccountCardItem("Nombre de usuario")
                AccountCardItem("Correo")
                AccountCardItem("Contraseña")

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("Cerrar sesión",
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}