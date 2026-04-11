package com.wiwiiwiii.lsmapp.data

import com.wiwiiwiii.lsmapp.data.model.Seccion
import com.wiwiiwiii.lsmapp.data.model.Tema

fun fakeData(): List<Seccion> {
    return listOf(

        Seccion(
            id = 1,
            titulo = "Introducción",
            temas = listOf(
                Tema(1, "Tema 1", listOf(
                    "letras_amo",
                    "letras_pl",
                    "letras_ll",
                    "letras_se",
                    "letras_ti"
                )),
                Tema(2, "Tema 2", listOf()),
                Tema(3, "Tema 3", listOf())
            )
        ),

        Seccion(
            id = 2,
            titulo = "Familia",
            temas = listOf(
                Tema(4, "Tema 4", listOf()),
                Tema(5, "Tema 5", listOf()),
                Tema(6, "Tema 6", listOf())
            )
        )
    )
}