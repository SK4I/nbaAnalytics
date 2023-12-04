package com.example.nbaanalytics.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Red
            )
        }
    }
}

sealed class BottomNavItem(var title: String, var icon: Int, var route: String){
    object Calendar: BottomNavItem("Calendar", androidx.core.R.drawable.notification_bg_normal, "calendar" )
    object Standings: BottomNavItem("Standings", androidx.core.R.drawable.notification_bg_normal, "standings" )
    object Favourites: BottomNavItem("Favourites", androidx.core.R.drawable.notification_bg_normal, "favourites" )
    object Profile: BottomNavItem("Profile", androidx.core.R.drawable.notification_bg_normal, "profile" )
}