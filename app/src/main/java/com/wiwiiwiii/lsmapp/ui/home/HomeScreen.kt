package com.wiwiiwiii.lsmapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.wiwiiwiii.lsmapp.data.fakeData
import com.wiwiiwiii.lsmapp.data.parseLessons
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.ui.home.components.SectionBlock
import com.wiwiiwiii.lsmapp.ui.home.components.TopBar
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    progressViewModel: ProgressViewModel
) {

    val context = LocalContext.current
    val lessons = parseLessons(context).sortedBy { it.order }

    var unlockedLevel by remember { mutableStateOf(1) }
    val secciones = fakeData()

    val lessonIds = lessons.map { it.id }
    val nextLessonId = progressViewModel.getNextLessonId(lessonIds)


    Column {

        TopBar(progressViewModel)

        Divider(color = MaterialTheme.colorScheme.primary)

        LazyColumn {
            items(secciones) { seccion ->
                SectionBlock(
                    seccion = seccion,
                    navController = navController,
                    progressViewModel = progressViewModel,
                    unlockedLevel = unlockedLevel,
                    nextLessonId = nextLessonId,
                    lessons = lessons
                )
            }
        }
    }
}

