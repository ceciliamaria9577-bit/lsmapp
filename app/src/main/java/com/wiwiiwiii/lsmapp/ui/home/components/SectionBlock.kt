package com.wiwiiwiii.lsmapp.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.data.model.Lesson
import com.wiwiiwiii.lsmapp.data.model.Seccion
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel

@Composable
fun SectionBlock(
    seccion: Seccion,
    navController: NavController,
    progressViewModel: ProgressViewModel,
    unlockedLevel: Int,
    nextLessonId: String?,
    lessons: List<Lesson>
) {

    var globalIndex = 1

    Column {

        Divider(color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Sección ${seccion.id}",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                seccion.titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        seccion.temas.forEachIndexed { index, tema ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {

                //  HEADER
                TemaHeader(
                    titulo = tema.titulo,
                    numeroTema = index + 1
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row {

                    //  TIMELINE
                    Column(
                        modifier = Modifier.width(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        val completedLessons = tema.lessonIds.count {
                            progressViewModel.isLessonCompleted(it)
                        }

                        val totalPoints = tema.lessonIds.size + 1

                        repeat(totalPoints) { index ->

                            val isCompleted = when {
                                index == 0 -> true
                                else -> index <= completedLessons
                            }

                            val isLineCompleted = index < completedLessons

                            TimelineItem(
                                isCompleted = isCompleted,
                                isLineCompleted = isLineCompleted,
                                isLast = index == totalPoints - 1
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    //  LECCIONES
                    Column(modifier = Modifier.weight(1f)
                        .padding(end = 16.dp)) {

                        tema.lessonIds.forEach { lessonId ->

                            val isCompleted = progressViewModel.isLessonCompleted(lessonId)

                            val isUnlocked = when {
                                isCompleted -> true
                                lessonId == nextLessonId -> true
                                else -> false
                            }

                            val lesson = lessons.find { it.id == lessonId }

                            LessonItem(
                                titulo = lesson?.title ?: "Lección $lessonId",
                                numero = lesson?.order ?: globalIndex,
                                desbloqueado = isUnlocked,
                                onClick = {
                                    if (isUnlocked) {
                                        navController.navigate("lesson/$lessonId")
                                    }
                                }
                            )

                            globalIndex++

                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
