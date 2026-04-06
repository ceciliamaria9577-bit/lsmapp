package com.wiwiiwiii.lsmapp.data

import com.wiwiiwiii.lsmapp.data.model.Seccion
import com.wiwiiwiii.lsmapp.data.model.Tema

fun fakeData(): List<Seccion> {
    return listOf(

        Seccion(
            id = 1,
            titulo = "Introducción",
            temas = listOf(
                Tema(1, "Tema 1", listOf(1, 2)),
                Tema(2, "Tema 2", listOf(3, 4)),
                Tema(3, "Tema 3", listOf(5, 6))
            )
        ),

        Seccion(
            id = 2,
            titulo = "Familia",
            temas = listOf(
                Tema(4, "Tema 4", listOf(7, 8)),
                Tema(5, "Tema 5", listOf(9, 10)),
                Tema(6, "Tema 6", listOf(11, 12))
            )
        )
    )
}