package com.test.movie.core

import com.test.movie.core.ApiConstants.createBaseUrl
import com.test.movie.core.remote.response.MovieListResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getPlayingMovies(
        @Url url: String = createBaseUrl(ApiConstants.NOW_PLAYING)
    ): Response<MovieListResponseModel>

    @GET
    suspend fun getPopularMovies(
        @Url url: String = createBaseUrl(ApiConstants.POPULAR)
    ): Response<MovieListResponseModel>

    @GET
    suspend fun getTopRatedMovies(
        @Url url: String = createBaseUrl(ApiConstants.TOP_RATED)
    ): Response<MovieListResponseModel>

}