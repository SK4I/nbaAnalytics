package com.example.nbaanalytics.di

import com.example.nbaanalytics.network.client.OkHttpClientProvider
import com.example.nbaanalytics.network.services.GamesNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
class UiModule {

}