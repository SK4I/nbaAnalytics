package com.example.nbaanalytics.ui.screens.calendar

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.LocalDate

private var todayDate = LocalDate.now().toString()

@Composable
fun CalendarContentScreen(viewModel: CalendarContentViewModel = hiltViewModel()) {
    viewModel.viewState.buildUI(
        onRefresh = {
            viewModel.fetchData(todayDate)
        },
        ui = {
            ContentScreen()
        })
}

@Composable
private fun ContentScreen() {
    TopBarCalendar { chosenDate ->
        todayDate = chosenDate.toString()
    }
}