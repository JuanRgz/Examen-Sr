package com.development.cursoandroid.repository

import com.development.cursoandroid.core.InternetCheck
import com.development.cursoandroid.data.local.LocalMovieDataSource
import com.development.cursoandroid.data.model.MovieList
import com.development.cursoandroid.data.model.toMovieEntity
import com.development.cursoandroid.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDataSource,
                            private val dataSourceLocal: LocalMovieDataSource): MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList {
        return if(InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if(InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else{
            dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if(InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else{
            dataSourceLocal.getPopularMovies()
        }
    }
}