package com.example.nbaanalytics.ui.screens.calendar

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaanalytics.data.repository.TodayGamesRepository
import com.example.nbaanalytics.network.base.Error
import com.example.nbaanalytics.ui.state.PageViewState
import com.example.network_services.network.models.GamesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarContentViewModel @Inject constructor(private val todayGamesRepository: TodayGamesRepository) :
    ViewModel() {

    var viewState by mutableStateOf<PageViewState>(PageViewState.LoadingContent)
        private set

    var todayGamesData by mutableStateOf(listOf<GamesResponse>())
        private set

    private val todayDate = LocalDate.now().toString()

    suspend fun fetchData(todayDate: String) {
        todayGamesRepository.getTodayGames(todayDate).handleResult(::handleSuccess, ::handleFailure)
    }

    init {
        viewModelScope.launch {
            //TODO replace with actual api call when testing ui
            viewState = PageViewState.LoadedContent
            //fetchData(todayDate)
        }
    }

    private fun handleSuccess(data: Any) {
        val games = (data as List<GamesResponse>)
        todayGamesData = games
        viewState = PageViewState.LoadedContent
    }

    private fun handleFailure(error: Error) {
        viewState = PageViewState.Error(error.toString())
    }
}