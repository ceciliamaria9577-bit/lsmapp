package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.wiwiiwiii.lsmapp.model.Seccion

@Composable
fun SectionBlock(seccion: Seccion) {

    Column {

        Divider()

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sección", color = Color.Gray)
            Text(seccion.titulo)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {

            Timeline(seccion.temas.size + 1)

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                seccion.temas.forEach {
                    TemaCardExact(it)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}