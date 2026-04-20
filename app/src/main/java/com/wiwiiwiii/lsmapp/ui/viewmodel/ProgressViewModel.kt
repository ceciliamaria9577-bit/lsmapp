package com.wiwiiwiii.lsmapp.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class ProgressViewModel : ViewModel() {

    var completedLessons by mutableStateOf(setOf<String>())
        private set

    var points by mutableStateOf(30)
        private set

    //  COMPLETAR LECCIÓN
    fun completeLesson(lessonId: String) {
        completedLessons = completedLessons + lessonId
        println("COMPLETED: $lessonId → $completedLessons")
    }

    //  OBTENER SIGUIENTE LECCIÓN (para desbloqueo)
    fun getNextLessonId(lessonIds: List<String>): String? {
        return lessonIds.firstOrNull { lessonId ->
            lessonId !in completedLessons
        }
    }

    fun isLessonCompleted(lessonId: String): Boolean {
        return lessonId in completedLessons
    }

    //  PROGRESO (0 o 1 por ahora)
    fun getProgress(lessonId: String): Int {
        return if (lessonId in completedLessons) 1 else 0
    }

    //  PUNTOS
    fun addPoints(amount: Int) {
        points += amount
        println("SUMANDO PUNTOS: +$amount → TOTAL: $points")
    }
}