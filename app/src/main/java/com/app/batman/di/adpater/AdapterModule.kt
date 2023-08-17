package com.app.batman.di.adpater

import com.app.batman.adapters.FilmsAdapter
import com.app.batman.adapters.RateAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Singleton
    @Provides
    fun provideFilmAdapter(): FilmsAdapter {
        return FilmsAdapter()
    }

    @Singleton
    @Provides
    fun provideRateAdapter(): RateAdapter {
        return RateAdapter()
    }
}