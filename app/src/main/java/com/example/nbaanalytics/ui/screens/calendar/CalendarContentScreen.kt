package com.example.nbaanalytics.ui.screens.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

private var todayDate = LocalDate.now().toString()

@Composable
fun CalendarContentScreen(viewModel: CalendarContentViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    viewModel.viewState.buildUI(
        onRefresh = {
            scope.launch(Dispatchers.IO) {
                viewModel.fetchData(todayDate)
            }
        },
        ui = {
            ContentScreen()
        })
}

@Composable
private fun ContentScreen() {
    TopBarCalendar {chosenDate ->
        todayDate = chosenDate.toString()
    }
}