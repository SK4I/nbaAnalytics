package com.example.nbaanalytics.ui.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PageErrorScreen(msg: String, onRefresh: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { onRefresh.invoke() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Refresh")
        }
        Text(text = msg, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}