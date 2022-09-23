package com.test.movie.core

import com.test.movie.core.remote.response.MovieListResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getPlayingMovies(
        @Query("api_key") key: String = ApiConstants.API_KEY,
        @Query("language") lang: String = ApiConstants.LANGUAGE
    ): Response<MovieListResponseModel>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = ApiConstants.API_KEY,
        @Query("language") lang: String = ApiConstants.LANGUAGE
    ): Response<MovieListResponseModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") key: String = ApiConstants.API_KEY,
        @Query("language") lang: String = ApiConstants.LANGUAGE
    ): Response<MovieListResponseModel>

}