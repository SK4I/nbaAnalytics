package com.example.nbaanalytics.di

import com.example.nbaanalytics.data.repository.TodayGamesRepository
import com.example.nbaanalytics.data.repository.TodayGamesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGamesRepository(gamesRepositoryImpl: TodayGamesRepositoryImpl): TodayGamesRepository
}