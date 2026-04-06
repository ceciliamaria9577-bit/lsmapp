package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.data.model.Seccion
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel

@Composable
fun SectionBlock(
    seccion: Seccion,
    navController: NavController,
    progressViewModel: ProgressViewModel,
    unlockedLevel: Int,
    nextLessonId: Int?
) {

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
                seccion.temas.forEach { tema ->

                    val completedLessonsInTema = tema.lessonIds.count {
                        progressViewModel.isLessonCompleted(it)
                    }

                    TemaItem(
                        tema = tema,
                        navController = navController,
                        progressViewModel = progressViewModel,
                        progress = completedLessonsInTema,
                        totalLessons = tema.lessonIds.size,
                        isUnlocked = tema.id <= unlockedLevel,
                        nextLessonId = nextLessonId
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
