package com.development.cursoandroid.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.development.cursoandroid.core.Resource
import com.development.cursoandroid.data.remote.MovieDataSource
import com.development.cursoandroid.databinding.FragmentMovieBinding
import com.development.cursoandroid.presentation.MovieViewModel
import com.development.cursoandroid.presentation.MovieViewModelFactory
import com.development.cursoandroid.repository.MovieRepositoryImpl
import com.development.cursoandroid.repository.RetrofitClient
import com.development.cursoandroid.repository.WebService

class MovieFragment : Fragment() {

    lateinit var bind: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> { MovieViewModelFactory(MovieRepositoryImpl(
        MovieDataSource(RetrofitClient.webservice)
    )) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentMovieBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> { bind.progressBar.visibility = View.VISIBLE }
                is Resource.Success -> {
                    bind.progressBar.visibility = View.GONE
                    Log.d("LiveData", "${result.data.first}")
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                    bind.progressBar.visibility = View.GONE

                }
            }
        })
    }
}