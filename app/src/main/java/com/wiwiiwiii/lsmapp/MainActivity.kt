package com.wiwiiwiii.lsmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.wiwiiwiii.lsmapp.ui.components.BottomNavBar
import com.wiwiiwiii.lsmapp.ui.screens.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    // 🔥 Detectar pantalla actual
    val currentRoute = navController
        .currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {

            // ✅ SOLO mostrar en estas pantallas
            if (currentRoute in listOf("home", "bookmark", "profile")) {
                BottomNavBar(navController)
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "welcome",
            modifier = Modifier.padding(padding)
        ) {

            composable("welcome") { WelcomeScreen(navController) }

            composable("auth_choice") { AuthChoiceScreen(navController) }

            composable("login") { LoginScreen(navController) }

            composable("register") { RegisterScreen(navController) }

            composable("personalization") { PersonalizationScreen(navController) }

            composable("home") { HomeScreen() }

            composable("bookmark") { BookmarkScreen() }

            composable("profile") { ProfileScreen() }
        }
    }
}
