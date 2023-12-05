package com.example.nbaanalytics.network.services

import com.example.nbaanalytics.data.models.GamesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GamesService {
    @Headers(
        "X-RapidAPI-Key", "629096a94cmshe968509d3a9fbb0p188876jsn327d6c0066cc",
        "X-RapidAPI-Host", "tank01-fantasy-stats.p.rapidapi.com"
    )
    @GET("getNBAScoresOnly?topPerformers=true&lineups=true")
    suspend fun listOfTodayGames(
        @Query("gameDate") todayDate: String
    ): Response<GamesModel>
}