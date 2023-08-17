package com.app.batman.di.repositories

import com.app.batman.database.FilmsDao
import com.app.batman.repositories.FilmDetailRepository
import com.app.batman.repositories.FilmRepository
import com.app.batman.services.FilmsWebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun filmsRepository(
        filmsWebServices: FilmsWebServices, filmsDao: FilmsDao
    ): FilmRepository {
        return FilmRepository(filmsWebServices, filmsDao)
    }

    @Singleton
    @Provides
    fun filmDetailsRepository(
        filmsWebServices: FilmsWebServices, filmsDao: FilmsDao
    ): FilmDetailRepository {
        return FilmDetailRepository(filmsWebServices, filmsDao)
    }
}