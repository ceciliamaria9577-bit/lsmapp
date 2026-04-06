package com.wiwiiwiii.lsmapp.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class ProgressViewModel : ViewModel() {

    var completedLessons by mutableStateOf(setOf<Int>())
        private set

    var points by mutableStateOf(30)
        private set

    fun completeLesson(lessonId: Int) {
        completedLessons = completedLessons + lessonId
    }

    fun isLessonCompleted(lessonId: Int): Boolean {
        return lessonId in completedLessons
    }

    fun getProgress(lessonId: Int): Int {
        return if (lessonId in completedLessons) 1 else 0
    }

    fun addPoints(amount: Int) {
        points += amount
        println("SUMANDO PUNTOS: +$amount → TOTAL: $points")
    }

    fun getNextLessonId(allLessons: List<Int>): Int? {
        return allLessons.firstOrNull { it !in completedLessons }
    }
}