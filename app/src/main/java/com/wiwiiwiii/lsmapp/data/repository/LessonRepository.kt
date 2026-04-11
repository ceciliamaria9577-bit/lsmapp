package com.wiwiiwiii.lsmapp.data.repository

import android.content.Context
import com.wiwiiwiii.lsmapp.data.model.Lesson
import com.wiwiiwiii.lsmapp.data.parseLessons

class LessonRepository(private val context: Context) {

    private val lessonList: List<Lesson> by lazy {
        parseLessons(context)
    }

    fun getLessons(): List<Lesson> = lessonList

    fun getLessonById(id: String): Lesson {
        return lessonList.first { it.id == id }
    }
}