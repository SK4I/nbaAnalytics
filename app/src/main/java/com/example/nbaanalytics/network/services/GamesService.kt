package com.example.nbaanalytics.network.services

import com.example.network_services.network.models.GamesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GamesService {
    @Headers(
        "X-RapidAPI-Host: api-basketball.p.rapidapi.com",
        "X-RapidAPI-Key: 629096a94cmshe968509d3a9fbb0p188876jsn327d6c0066cc"
    )
    @GET("games?season=2023-2024")
    suspend fun listOfTodayGames(
        @Query("league") league: String,
        @Query("date") todayDate: String
    ): Response<GamesModel>
}