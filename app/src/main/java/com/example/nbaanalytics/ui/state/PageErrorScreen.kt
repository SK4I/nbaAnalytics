package com.example.nbaanalytics.ui.state

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun PageErrorScreen(msg: String){
    Text(text = msg)
}