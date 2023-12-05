package com.example.nbaanalytics.network.services

import com.example.nbaanalytics.network.client.OkHttpClientProvider
import com.example.network_services.network.models.GamesModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class GamesNetworkService @Inject constructor(private val okHttpClientProvider: OkHttpClientProvider) {

    companion object {
        private const val GAMES_URL = "https://api-basketball.p.rapidapi.com/"
        private const val NBA_LEAGUE_ID = "12"
    }

    suspend fun getTodayGames(todayDate: String): Response<GamesModel> {
        val retro = Retrofit.Builder()
            .baseUrl(GAMES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientProvider.getOkHttpClientBuilder())
            .build()

        val service = retro.create(GamesService::class.java)
        return service.listOfTodayGames(NBA_LEAGUE_ID, todayDate)
    }
}

