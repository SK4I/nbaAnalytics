package com.example.nbaanalytics.ui.screens.calendar

import android.graphics.pdf.PdfDocument.Page
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaanalytics.data.repository.TodayGamesRepository
import com.example.nbaanalytics.network.services.GamesNetworkService
import com.example.nbaanalytics.ui.state.PageViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarContentViewModel @Inject constructor(private val todayGamesRepository: TodayGamesRepository) : ViewModel() {

    var viewState by mutableStateOf<PageViewState>(PageViewState.LoadedContent)
        private set

}