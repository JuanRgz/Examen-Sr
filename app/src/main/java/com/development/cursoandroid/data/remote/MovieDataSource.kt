package com.development.cursoandroid.data.remote

import com.development.cursoandroid.data.model.MovieList

class MovieDataSource {
    fun getUpcomingMovies(): MovieList{
        return MovieList()
    }
    fun getTopRatedMovies(): MovieList{
        return MovieList()
    }
    fun getPopularMovies(): MovieList{
        return MovieList()
    }
}