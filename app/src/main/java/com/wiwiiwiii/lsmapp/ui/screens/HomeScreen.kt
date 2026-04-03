package com.wiwiiwiii.lsmapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import com.wiwiiwiii.lsmapp.data.fakeData
import com.wiwiiwiii.lsmapp.ui.components.*

@Composable
fun HomeScreen() {

    val secciones = fakeData()

    Column {

        TopBar()

        Divider()

        LazyColumn {

            items(secciones) { seccion ->
                SectionBlock(seccion)
            }
        }
    }
}