package com.wiwiiwiii.lsmapp.data

import com.wiwiiwiii.lsmapp.model.*

fun fakeData(): List<Seccion> {
    return listOf(

        Seccion(
            id = 1,
            titulo = "Introducción",
            temas = listOf(
                Tema(1, "Tema 1"),
                Tema(2, "Tema 2"),
                Tema(3, "Tema 3")
            )
        ),

        Seccion(
            id = 2,
            titulo = "Familia",
            temas = listOf(
                Tema(4, "Tema 4"),
                Tema(5, "Tema 5"),
                Tema(6, "Tema 6")
            )
        )
    )
}