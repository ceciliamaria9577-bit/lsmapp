package com.wiwiiwiii.lsmapp.model

data class Seccion(
    val id: Int,
    val titulo: String,
    val temas: List<Tema>
)

data class Tema(
    val id: Int,
    val titulo: String,
    val videoUrl: String? = null,
    val imagen: Int? = null
)