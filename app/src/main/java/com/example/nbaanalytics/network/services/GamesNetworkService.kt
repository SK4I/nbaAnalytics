package com.example.nbaanalytics.network.services

import com.example.nbaanalytics.data.models.GamesModel
import com.example.nbaanalytics.network.client.OkHttpClientProvider
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class GamesNetworkService @Inject constructor(private val okHttpClientProvider: OkHttpClientProvider) {

    companion object {
        private const val GAMES_URL = "https://tank01-fantasy-stats.p.rapidapi.com/"
    }

    suspend fun getTodayGames(todayDate: String): Response<GamesModel> {
        val retro = Retrofit.Builder()
            .baseUrl(GAMES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientProvider.getOkHttpClientBuilder())
            .build()

        val service = retro.create(GamesService::class.java)
        return service.listOfTodayGames(todayDate)
    }
}

