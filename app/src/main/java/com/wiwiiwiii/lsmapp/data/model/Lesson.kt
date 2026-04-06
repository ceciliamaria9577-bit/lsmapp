package com.wiwiiwiii.lsmapp.data.model

data class Lesson(
    val id: Int,
    val title: String,
    val steps: List<LessonStep>
)

data class LessonStep(
    val type: String,
    val text: String? = null,
    val title: String? = null,
    val image: String? = null,
    val question: String? = null,
    val options: List<String>? = null,
    val correct: String? = null,
    val pairs: List<MatchPair>? = null
)

data class MatchPair(
    val word: String,
    val image: String
)