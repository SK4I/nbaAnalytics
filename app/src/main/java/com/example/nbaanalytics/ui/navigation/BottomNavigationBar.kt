package com.example.nbaanalytics.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nbaanalytics.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Calendar,
        BottomNavItem.Standings,
        BottomNavItem.Favourites,
        BottomNavItem.Profile,
    )

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.teal_200),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = Color.Black
                    )
                },
                label = {
                    Text(text = item.title,)
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black
            )
        }
    }
}

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {
    object Calendar : BottomNavItem("Calendar", Icons.Default.Home, "calendar")
    object Standings : BottomNavItem("Standings", Icons.Default.Home, "standings")
    object Favourites :
        BottomNavItem("Favourites", Icons.Default.Home, "favourites")

    object Profile : BottomNavItem("Profile", Icons.Default.Home, "profile")
}