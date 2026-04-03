package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.wiwiiwiii.lsmapp.model.Tema

@Composable
fun TemaCardExact(tema: Tema) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEFEFEF)
        )
    ) {

        Column(modifier = Modifier.padding(12.dp)) {

            Row {

                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .background(Color.Gray, RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text("Título", fontWeight = FontWeight.Bold)
                    Text(
                        tema.titulo,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .padding(horizontal = 2.dp)
                            .background(
                                Color.LightGray,
                                RoundedCornerShape(3.dp)
                            )
                    )
                }
            }
        }
    }
}