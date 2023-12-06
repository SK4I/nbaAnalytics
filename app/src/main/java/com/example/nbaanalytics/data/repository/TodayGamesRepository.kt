package com.example.nbaanalytics.data.repository

import com.example.nbaanalytics.network.base.Error
import com.example.nbaanalytics.network.base.Result
import com.example.network_services.network.models.GamesResponse

interface TodayGamesRepository {
    suspend fun getTodayGames(todayDate: String): Result<List<GamesResponse>, Error>
}