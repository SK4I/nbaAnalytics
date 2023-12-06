package com.example.nbaanalytics.data.repository

import com.example.nbaanalytics.network.base.Result
import com.example.nbaanalytics.network.services.GamesNetworkService
import com.example.network_services.network.models.GamesResponse
import javax.inject.Inject
import com.example.nbaanalytics.network.base.Error

class TodayGamesRepositoryImpl @Inject constructor(private val gamesNetworkService: GamesNetworkService) :
    TodayGamesRepository {
    override suspend fun getTodayGames(todayDate: String): Result<List<GamesResponse>, Error> {
        return try {
            val response = gamesNetworkService.getTodayGames(todayDate)

            if (response.isSuccessful && response.body()?.errors.isNullOrEmpty() && response.body()?.response != null) {
                Result.Success(response.body()!!.response)
            } else {
                Result.Failure(Error.ResponseError)
            }
        } catch (error: Exception) {
            Result.Failure(Error.NetworkError)
        }
    }
}