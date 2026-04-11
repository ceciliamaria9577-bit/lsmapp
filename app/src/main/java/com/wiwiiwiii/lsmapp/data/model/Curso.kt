package com.wiwiiwiii.lsmapp.data.model

data class Seccion(
    val id: Int,
    val titulo: String,
    val temas: List<Tema>
)

data class Tema(
    val id: Int,
    val titulo: String,
    val lessonIds: List<String>,
    val videoUrl: String? = null,
    val imagen: Int? = null,
    var progress: Int = 0
)