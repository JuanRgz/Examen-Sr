package com.test.movie.ui.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.movie.core.GenericError
import com.test.movie.core.GenericResponse
import com.test.movie.core.StatusEnum
import com.test.movie.core.remote.MovieRepository
import com.test.movie.core.remote.response.MovieListResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val repository = MovieRepository()
    val playingMovies by lazy { GenericResponse<MovieListResponseModel, GenericError>(StatusEnum.LOADING) }
    val popularMovies by lazy { GenericResponse<MovieListResponseModel, GenericError>(StatusEnum.LOADING) }
    val topRatedMovies by lazy { GenericResponse<MovieListResponseModel, GenericError>(StatusEnum.LOADING) }

    fun getPlayingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            playingMovies.postValue(GenericResponse(StatusEnum.LOADING))
            repository.getPlayingMovies(playingMovies)
        }
    }

    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            popularMovies.postValue(GenericResponse(StatusEnum.LOADING))
            repository.getPopularMovies(popularMovies)
        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            topRatedMovies.postValue(GenericResponse(StatusEnum.LOADING))
            repository.getTopRatedMovies(topRatedMovies)
        }
    }
}