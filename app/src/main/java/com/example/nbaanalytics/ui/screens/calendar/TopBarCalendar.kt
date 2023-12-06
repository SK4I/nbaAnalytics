package com.example.nbaanalytics.ui.screens.calendar

import androidx.compose.runtime.Composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CalendarItem(
    modifier: Modifier,
    day: LocalDate,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        color = Color.Transparent,
        contentColor = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .background(
                    shape = CircleShape,
                    color = if (day.dayOfMonth == LocalDate.now().dayOfMonth && !isSelected) Color.LightGray else Color.Transparent
                )
        ) {
            val dayOfWeek = day.dayOfWeek.toString().take(3)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                // Display day number and first three letters of the weekday name
                Text(day.dayOfMonth.toString())
                Text(dayOfWeek)
            }
        }
    }
}

// Function to generate a list of days centered around today's date
private fun generateDaysCenteredOnToday(): List<LocalDate> {
    val today = LocalDate.now()
    val days = mutableListOf<LocalDate>()
    val daysToShow = 90 // Change this value to show more or fewer days

    for (i in -daysToShow / 2..daysToShow / 2) {
        val date = today.plusDays(i.toLong())
        days.add(date)
    }
    return days
}

@SuppressLint("RememberReturnType")
@Composable
fun TopBarCalendar(onDaySelected: (LocalDate) -> Unit) {
    val daysOfMonth = remember { generateDaysCenteredOnToday() }
    val lazyListState = rememberLazyListState()
    val selectedDay = remember { mutableStateOf(LocalDate.now()) }
    val scope = rememberCoroutineScope()
    val currentMonthName = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }

    var scrollOffset by remember { mutableIntStateOf(0) }
    var totalWidth by remember { mutableIntStateOf(0) }
    var containerWidth by remember { mutableIntStateOf(0) }

    scrollOffset = (-(containerWidth / 2) + totalWidth / 1.8).toInt()
    Column {
        Text(
            text = daysOfMonth[currentMonthName.value].month.name,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        LazyRow(
            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                containerWidth = layoutCoordinates.size.width
            },
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemsIndexed(daysOfMonth) { index, day ->
                val isSelected = selectedDay.value == day
                CalendarItem(
                    modifier = Modifier
                        .onGloballyPositioned { layoutCoordinates ->
                            totalWidth = layoutCoordinates.size.width
                        }
                        .padding(8.dp)
                        .background(
                            if (isSelected) MaterialTheme.colors.primary else Color.Transparent
                        ),
                    day = day,
                    isSelected = isSelected,
                    onClick = {
                        selectedDay.value = day
                        onDaySelected(day)
                        scope.launch {
                            val selectedScrollIndex = daysOfMonth.indexOf(selectedDay.value)
                            lazyListState.animateScrollToItem(
                                selectedScrollIndex, scrollOffset
                            )
                        }
                    })
            }
        }
    }

    // Scroll to the selected day
    //TODO make an actual calculation
    remember {
        val selectedIndex = daysOfMonth.indexOf(selectedDay.value)
        scope.launch {
            lazyListState.scrollToItem(selectedIndex, -440)
        }
    }
}