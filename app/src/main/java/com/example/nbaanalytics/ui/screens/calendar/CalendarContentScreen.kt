package com.example.nbaanalytics.ui.screens.calendar

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CalendarContentScreen(viewModel: CalendarContentViewModel = hiltViewModel()){
    viewModel.viewState.buildUI {
        ContentScreen()
    }
}

@Composable
private fun ContentScreen(){

}