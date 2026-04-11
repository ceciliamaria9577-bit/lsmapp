package com.wiwiiwiii.lsmapp.data.model

import com.wiwiiwiii.lsmapp.ui.lesson.model.StepType

data class Lesson(
    val id: String,
    val order: Int,
    val title: String,
    val steps: List<LessonStep>)

data class LessonStep(
    val type: StepType,
    val text: String? = null,
    val title: String? = null,
    val image: String? = null,
    val question: String? = null,
    val options: List<String>? = null,
    val correct: String? = null,

    val pairs: List<MatchPair>? = null,

    val media: String? = null,
    val letters: List<String>? = null
)

data class MatchPair(
    val word: String,
    val image: String
)

data class OptionItem(
    val text: String? = null,
    val media: String? = null,
    val letter: String? = null
)