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

    fun fetchData(todayDate: String) {
        Log.d("fsfsef", "handleSuccess: "+ todayDate)
        viewModelScope.launch {
            todayGamesRepository.getTodayGames(todayDate)
                .handleResult(::handleSuccess, ::handleFailure)
        }
    }

    init {
        fetchData(todayDate)
    }

    private fun handleSuccess(data: Any) {
        val games = (data as List<GamesResponse>)
        Log.d("fsfsef", "handleSuccess: "+ games.toString())
        todayGamesData = games
        viewState = PageViewState.LoadedContent
    }

    private fun handleFailure(error: Error) {
        Log.d("fsfsef", "handleError: ")
        viewState = PageViewState.Error(error.toString())
    }
}