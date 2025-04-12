package com.development.cursoandroid.data.remote

import com.development.cursoandroid.application.ApiConstants
import com.development.cursoandroid.data.model.MovieList
import com.development.cursoandroid.repository.WebService

class MovieDataSource(private val webService: WebService) {
    suspend fun getUpcomingMobvies(): MovieList = webService.getUpcomingMovies(ApiConstants.API_KEY, ApiConstants.LANGUAGE)

    suspend fun getTopRatedMbovies(): MovieList = webService.getTopRatedMovies(ApiConstants.API_KEY, ApiConstants.LANGUAGE)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(ApiConstants.API_KEY, ApiConstants.LANGUAGE)
}