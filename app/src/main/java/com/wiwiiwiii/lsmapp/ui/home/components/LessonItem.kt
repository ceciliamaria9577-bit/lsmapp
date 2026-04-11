package com.wiwiiwiii.lsmapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LessonItem(
    titulo: String,
    numero: Int,
    desbloqueado: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(16.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Imagen izquierda
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(12.dp)
                )
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Lección $numero",
            )

            Text(
                text = titulo,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Button(
                onClick = onClick,
                enabled = desbloqueado,
                shape = RoundedCornerShape(50)
            ) {
                Text(if (desbloqueado) "Comenzar" else "Bloqueado")
            }
        }
    }
}