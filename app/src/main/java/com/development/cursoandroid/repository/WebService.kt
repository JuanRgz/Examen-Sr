package com.development.cursoandroid.repository

import com.development.cursoandroid.application.ApiConstants
import com.development.cursoandroid.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String, @Query("language") language: String): MovieList

    @GET("top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("language") language: String): MovieList

    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String, @Query("language") language: String): MovieList
}

object RetrofitClient{
    val webservice by lazy{
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}