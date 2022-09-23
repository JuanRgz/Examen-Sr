package com.test.movie.ui.fragment.home

import android.view.View
import com.test.movie.core.remote.response.MovieResponseModel
import com.test.movie.ui.utils.showToast

fun HomeFragment.showLoading(show: Boolean){
    bind.loading.visibility = if(show) View.VISIBLE else View.GONE
}

fun HomeFragment.showPlayingMovies(results: List<MovieResponseModel>) {
    bind.lblPlaying.visibility = View.VISIBLE
    bind.RVPlaying.adapter = MovieAdapter(results){ title, date ->
        //agregando evento onclick a cada item
        showToast("$title: $date")
    }
}

fun HomeFragment.showPopularMovies(results: List<MovieResponseModel>) {
    bind.lblPopular.visibility = View.VISIBLE
    bind.RVPopular.adapter = MovieAdapter(results){ title, date ->
        //agregando evento onclick a cada item
        showToast("$title: $date")
    }
}

fun HomeFragment.showTopRatedMovies(results: List<MovieResponseModel>) {
    bind.lblTopRated.visibility = View.VISIBLE
    bind.RVRated.adapter = MovieAdapter(results){ title, date ->
        //agregando evento onclick a cada item
        showToast("$title: $date")
    }
}