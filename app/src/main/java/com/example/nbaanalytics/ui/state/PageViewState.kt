package com.example.nbaanalytics.ui.state

import androidx.compose.runtime.Composable

sealed class PageViewState {
    @Composable
    abstract fun buildUI(
        ui: @Composable() () -> Unit
    )

    object LoadingContent : PageViewState() {
        @Composable
        override fun buildUI(ui: @Composable () -> Unit) {
            PageLoadingContentScreen()
        }
    }

    object LoadedContent : PageViewState() {
        @Composable
        override fun buildUI(ui: @Composable () -> Unit) {
            ui()
        }
    }

    class Error(private val msg: String) : PageViewState(){
        @Composable
        override fun buildUI(ui: @Composable () -> Unit) {
            PageErrorScreen(msg)
        }
    }
}
