package com.example.nbaanalytics.ui.screens.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.network_services.network.models.GamesResponse
import java.time.LocalDate

private var todayDate = LocalDate.now().toString()

@Composable
fun CalendarContentScreen(viewModel: CalendarContentViewModel = hiltViewModel()) {
    viewModel.viewState.buildUI(
        onRefresh = {
            viewModel.fetchData(todayDate)
        },
        ui = {
            ContentScreen(viewModel)
        })
}

@Composable
private fun ContentScreen(viewModel: CalendarContentViewModel) {
    val listState = rememberLazyListState()
    Column {
        TopBarCalendar { chosenDate ->
            todayDate = chosenDate.toString()
            viewModel.fetchData(chosenDate.toString())
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            state = listState
        ) {
            items(viewModel.todayGamesData) { value ->
                GamesView(game = value)
            }
        }
    }
}

@Composable
fun GamesView(game: GamesResponse) {
    val homeScore = game.scores.home?.total
    val awayScore = game.scores.away?.total
    Column(modifier = Modifier.clickable {

    }) {
        Text(text = game.date.toString())
        Box {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    LogoImage(
                        game.teams.home?.logo,
                        Modifier
                            .size(26.dp)
                            .padding(end = 5.dp)
                    )
                    Text(text = game.teams.home?.name.toString())
                }
                Text(
                    text = game.scores.home?.total.toString(),
                    modifier = Modifier.graphicsLayer {
                        alpha = if (homeScore == null) 0.0F
                        else 1.0F
                    })
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Text(text = game.teams.away?.name.toString())
                    LogoImage(
                        game.teams.away?.logo,
                        Modifier
                            .size(26.dp)
                            .padding(start = 5.dp)
                    )
                }
                Text(text = game.scores.away?.total.toString(),
                    modifier = Modifier.graphicsLayer {
                        alpha = if (awayScore == null) 0.0F
                        else 1.0F
                    })
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Divider(
        color = Color.Black, modifier = Modifier
            .fillMaxWidth()
            .width(1.dp)
    )
}

@Composable
fun LogoImage(url: String?, modifier: Modifier) {
    val painter = rememberAsyncImagePainter(model = url)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
    )
}