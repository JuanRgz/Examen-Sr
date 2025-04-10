package com.development.cursoandroid.repository

import com.development.cursoandroid.data.model.MovieList
import com.development.cursoandroid.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource): MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()
}