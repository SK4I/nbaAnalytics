package com.example.nbaanalytics.ui.state

import androidx.compose.runtime.Composable

sealed class PageViewState {
    @Composable
    abstract fun buildUI(
        onRefresh: () -> Unit,
        ui: @Composable() () -> Unit
    )

    data object LoadingContent : PageViewState() {
        @Composable
        override fun buildUI(
            onRefresh: () -> Unit,
            ui: @Composable () -> Unit
        ) {
            PageLoadingContentScreen()
        }
    }

    data object LoadedContent : PageViewState() {
        @Composable
        override fun buildUI(
            onRefresh: () -> Unit,
            ui: @Composable () -> Unit
        ) {
            ui()
        }
    }

    class Error(private val msg: String) : PageViewState() {
        @Composable
        override fun buildUI(
            onRefresh: () -> Unit,
            ui: @Composable () -> Unit
        ) {
            PageErrorScreen(msg, onRefresh)
        }
    }
}
