package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.data.model.Tema
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel

@Composable
fun TemaItem(
    tema: Tema,
    navController: NavController,
    progressViewModel: ProgressViewModel,
    progress: Int,
    isUnlocked: Boolean,
    nextLessonId: Int?,
    totalLessons: Int
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEDEDED)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            //  IMAGEN (IZQUIERDA)
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(
                        Color(0xFFBDBDBD),
                        RoundedCornerShape(16.dp)
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            //  LADO DERECHO (TEXTO + BOTÓN + BARRAS)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(90.dp), // IMPORTANTE para alinear abajo
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                //  TEXTO
                Column {
                    Text(
                        text = "Título",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = "Tema ${tema.id}",
                        color = Color.DarkGray
                    )
                }

                //  BOTÓN (ALINEADO A LA DERECHA)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    if (isUnlocked) {
                        Box(
                            modifier = Modifier
                                .background(
                                    Color(0xFF9E9E9E),
                                    RoundedCornerShape(50)
                                )
                                .clickable {
                                    nextLessonId?.let {
                                        navController.navigate("lesson/$it")
                                    }
                                }
                                .padding(horizontal = 20.dp, vertical = 6.dp)
                        ) {
                            Text(
                                "Empezar",
                                color = Color.White
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .background(
                                    Color(0xFFBDBDBD),
                                    RoundedCornerShape(50)
                                )
                                .padding(horizontal = 20.dp, vertical = 6.dp)
                        ) {
                            Text(
                                "Bloqueado",
                                color = Color.White
                            )
                        }
                    }
                }

                //  RANURAS
                Row {
                    tema.lessonIds.forEachIndexed { index, lessonId ->

                        val completed = progressViewModel.isLessonCompleted(lessonId)

                        Box(
                            modifier = Modifier
                                .height(6.dp)
                                .weight(1f)
                                .padding(end = 4.dp)
                                .background(
                                    if (completed)
                                        Color(0xFF616161)
                                    else
                                        Color(0xFFBDBDBD),
                                    RoundedCornerShape(50)
                                )
                        )
                    }
                }
            }
        }
    }
}