package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun BottomNavBar(navController: NavController) {

    val items = listOf(
        "home",
        "bookmark",
        "profile"
    )

    NavigationBar {

        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry.value?.destination?.route

        items.forEach { screen ->

            NavigationBarItem(
                selected = currentRoute == screen,
                onClick = {
                    navController.navigate(screen)
                },
                icon = {
                    when (screen) {
                        "home" -> Icon(Icons.Default.Home, contentDescription = "")
                        "bookmark" -> Icon(Icons.Default.Bookmark, contentDescription = "")
                        "profile" -> Icon(Icons.Default.Person, contentDescription = "")
                    }
                }
            )
        }
    }
}