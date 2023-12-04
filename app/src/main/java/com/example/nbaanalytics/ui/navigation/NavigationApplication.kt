package com.example.nbaanalytics.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationApplication(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Calendar.route){
        composable(BottomNavItem.Calendar.route){
            CalendarScreen()
        }
        composable(BottomNavItem.Standings.route){
            StandingsScreen()
        }
        composable(BottomNavItem.Profile.route){
            ProfileScreen()
        }
        composable(BottomNavItem.Favourites.route){
            FavouritesScreen()
        }
    }
}