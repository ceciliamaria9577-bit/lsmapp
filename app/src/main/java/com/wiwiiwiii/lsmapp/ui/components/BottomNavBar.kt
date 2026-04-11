package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wiwiiwiii.lsmapp.R

@Composable
fun BottomNavBar(navController: NavController) {

    val items = listOf(
        "home",
        "bookmark",
        "profile"
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {

        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry.value?.destination?.route

        items.forEach { screen ->

            NavigationBarItem(
                selected = currentRoute == screen,
                onClick = { navController.navigate(screen) },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.background,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                icon = {
                    when (screen) {
                        "home" -> Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = "Home",
                            modifier = Modifier.size(36.dp),
                        )
                        "bookmark" -> Icon(
                            painter = painterResource(id = R.drawable.ic_bookmark),
                            contentDescription = "Bookmark",
                            modifier = Modifier.size(36.dp)
                        )
                        "profile" -> Icon(
                            painter = painterResource(id = R.drawable.ic_profile),
                            contentDescription = "Profile",
                            modifier = Modifier.size(46.dp)
                        )
                    }
                }
            )
        }
    }
}