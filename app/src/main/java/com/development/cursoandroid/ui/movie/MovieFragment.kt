package com.development.cursoandroid.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.development.cursoandroid.core.Resource
import com.development.cursoandroid.data.model.Movie
import com.development.cursoandroid.data.remote.MovieDataSource
import com.development.cursoandroid.databinding.FragmentMovieBinding
import com.development.cursoandroid.presentation.MovieViewModel
import com.development.cursoandroid.presentation.MovieViewModelFactory
import com.development.cursoandroid.repository.MovieRepositoryImpl
import com.development.cursoandroid.repository.RetrofitClient
import com.development.cursoandroid.repository.WebService
import com.development.cursoandroid.ui.movie.adapters.concat.MovieAdapter
import com.development.cursoandroid.ui.movie.adapters.concat.PopularConcatAdapter
import com.development.cursoandroid.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.development.cursoandroid.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(), MovieAdapter.OnMovieClickListener {

    lateinit var bind: FragmentMovieBinding
    private lateinit var concatAdapter: ConcatAdapter

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
        concatAdapter = ConcatAdapter()
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> { bind.progressBar.visibility = View.VISIBLE }
                is Resource.Success -> {
                    bind.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpcomingConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
                        addAdapter(1, TopRatedConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
                        addAdapter(2, PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))
                    }

                    bind.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    bind.progressBar.visibility = View.GONE

                }
            }
        })
        bind.btnFragment.setOnClickListener {
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToMainGraph())
        }
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.posterPath,
            movie.backdropPath,
            movie.voteAverage.toFloat(),
            movie.voteCount,
            movie.overview,
            movie.title,
            movie.originalLanguage,
            movie.releaseDate
        )
        findNavController().navigate(action)
    }
}