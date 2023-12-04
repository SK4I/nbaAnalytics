package com.example.nbaanalytics.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nbaanalytics.ui.screens.calendar.CalendarContentScreen
import com.example.nbaanalytics.ui.screens.favourites.FavouritesScreen
import com.example.nbaanalytics.ui.screens.profile.ProfileScreen
import com.example.nbaanalytics.ui.screens.standings.StandingsScreen

@Composable
fun NavigationApplication(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Calendar.route){
        composable(BottomNavItem.Calendar.route){
            CalendarContentScreen()
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