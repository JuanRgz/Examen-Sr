package com.test.movie.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.movie.core.remote.response.MovieResponseModel

@Database(entities = [MovieResponseModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}