package com.example.nbaanalytics.di

import android.content.Context
import com.example.nbaanalytics.network.client.OkHttpClientProvider
import com.example.nbaanalytics.network.interceptors.OfflineInterceptor
import com.example.nbaanalytics.network.interceptors.OnlineInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class NetworkModule {

    @Provides
    fun provideOfflineInterceptor(@ActivityContext context: Context) =
        OfflineInterceptor(context)

    @Provides
    fun provideOnlineInterceptor() = OnlineInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ActivityContext context: Context,
        offlineInterceptor: OfflineInterceptor,
        onlineInterceptor: OnlineInterceptor
    ) = OkHttpClientProvider(context, offlineInterceptor, onlineInterceptor)
}