package com.test.movie.core

object ApiConstants {
    const val API_KEY = "ae66f6be8f2a839435eb91fdc7bf2919"
    const val PATH_IMG = "https://image.tmdb.org/t/p/w500/"
    const val BASE_URL = "https://api.themoviedb.org/3/"

    //ENDPOINT
    const val NOW_PLAYING = "movie/now_playing?"
    const val POPULAR = "movie/popular?"
    const val TOP_RATED = "movie/top_rated?"

    fun createBaseUrl(endpoint: String): String {
        return BASE_URL.plus(endpoint) + "api_key=$API_KEY"
    }
}