package com.app.batman.di.db

import android.app.Application
import androidx.room.Room
import com.app.batman.core.Const
import com.app.batman.database.BatmanDb
import com.app.batman.database.FilmsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideAppDataBase(app: Application): BatmanDb {
        return Room.databaseBuilder(
            app,
            BatmanDb::class.java,
            Const.appDbName
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(batmanDb: BatmanDb): FilmsDao {
        return batmanDb.filmDao()
    }

}