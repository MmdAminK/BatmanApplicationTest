package com.app.batman.di.services

import com.app.batman.services.FilmsWebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun films(retrofit: Retrofit): FilmsWebServices {
        return retrofit.create(FilmsWebServices::class.java)
    }
    
}
