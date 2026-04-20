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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wiwiiwiii.lsmapp.data.repository.LessonRepository
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wiwiiwiii.lsmapp.ui.auth.AuthChoiceScreen
import com.wiwiiwiii.lsmapp.ui.auth.LoginScreen
import com.wiwiiwiii.lsmapp.ui.auth.PersonalizationScreen
import com.wiwiiwiii.lsmapp.ui.auth.RegisterScreen
import com.wiwiiwiii.lsmapp.ui.auth.WelcomeScreen
import com.wiwiiwiii.lsmapp.ui.home.HomeScreen
import com.wiwiiwiii.lsmapp.ui.library.LibraryScreen
import com.wiwiiwiii.lsmapp.ui.library.alphabet.AlphabetScreen
import com.wiwiiwiii.lsmapp.ui.library.alphabet.LetterDetailScreen
import com.wiwiiwiii.lsmapp.ui.profile.ProfileScreen
import com.wiwiiwiii.lsmapp.ui.profile.SettingsScreen
import com.wiwiiwiii.lsmapp.ui.theme.LsmappTheme
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LsmappTheme(darkTheme = false) {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val progressViewModel: ProgressViewModel = viewModel()

    //  Detectar pantalla actual
    val currentRoute = navController
        .currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {

            //  SOLO mostrar en estas pantallas
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

            composable("home") {
                HomeScreen(
                    navController = navController,
                    progressViewModel = progressViewModel
                )
            }
            composable("bookmark") { LibraryScreen(navController) }

            composable("alphabet") {
                AlphabetScreen(navController)
            }

            composable("profile") { ProfileScreen(progressViewModel, navController) }

            composable("lesson/{id}") { backStackEntry ->

                val context = LocalContext.current
                val repo = LessonRepository(context)

                val id = backStackEntry.arguments?.getString("id")!!
                val lesson = repo.getLessonById(id)

                LessonScreen(
                    lesson = lesson,
                    navController = navController,
                    progressViewModel = progressViewModel
                )
            }

            composable("letter_detail/{index}") { backStackEntry ->
                val index = backStackEntry.arguments?.getString("index")?.toInt() ?: 0
                LetterDetailScreen(navController, index)
            }

            composable("settings") {
                SettingsScreen(navController)
            }

        }
    }
}
