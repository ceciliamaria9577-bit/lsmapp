package com.wiwiiwiii.lsmapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.wiwiiwiii.lsmapp.data.fakeData
import com.wiwiiwiii.lsmapp.data.parseLessons
import com.wiwiiwiii.lsmapp.ui.components.*
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    progressViewModel: ProgressViewModel
) {

    val context = LocalContext.current
    val lessons = parseLessons(context)

    var unlockedLevel by remember { mutableStateOf(1) }
    val secciones = fakeData()

    val lessonIds = lessons.map { it.id }
    val nextLessonId = progressViewModel.getNextLessonId(lessonIds)


    Column {

        TopBar(progressViewModel)

        Divider()

        LazyColumn {
            items(secciones) { seccion ->
                SectionBlock(
                    seccion = seccion,
                    navController = navController,
                    progressViewModel = progressViewModel,
                    unlockedLevel = unlockedLevel,
                    nextLessonId = nextLessonId
                )
            }
        }
    }
}

