package com.example.nbaanalytics.ui.screens.calendar

import android.graphics.pdf.PdfDocument.Page
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaanalytics.ui.state.PageViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarContentViewModel @Inject constructor() : ViewModel() {

    var viewState by mutableStateOf<PageViewState>(PageViewState.LoadingContent)
        private set


    init {
        viewModelScope.launch {
            delay(2000)
            viewState = PageViewState.LoadedContent
        }
    }
}