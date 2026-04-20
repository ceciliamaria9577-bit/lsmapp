package com.wiwiiwiii.lsmapp.ui.navigation

sealed class Screen(val route: String) {
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}