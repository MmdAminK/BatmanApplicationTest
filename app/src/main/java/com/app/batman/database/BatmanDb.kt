package com.app.batman.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.batman.core.Converter
import com.app.batman.models.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class BatmanDb : RoomDatabase() {

    abstract fun filmDao(): FilmsDao
}
