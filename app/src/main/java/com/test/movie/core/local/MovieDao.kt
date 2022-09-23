package com.test.movie.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.movie.core.remote.response.MovieResponseModel

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<MovieResponseModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieResponseModel)

}