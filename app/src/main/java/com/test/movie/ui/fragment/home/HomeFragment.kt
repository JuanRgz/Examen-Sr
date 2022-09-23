package com.test.movie.ui.fragment.home

import androidx.fragment.app.viewModels
import com.test.movie.R
import com.test.movie.core.App
import com.test.movie.core.StatusEnum
import com.test.movie.databinding.FragmentHomeBinding
import com.test.movie.ui.common.BaseFragment
import com.test.movie.ui.utils.ResponseDialog
import com.test.movie.ui.utils.showToast


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel:HomeViewModel by viewModels()
    override fun initElements() {
        viewModel.apply{
            getPlayingMovies()
            getPopularMovies()
            getTopRatedMovies()
        }
    }

    override fun getLayout(): Int = R.layout.fragment_home

    override fun initObservers() {
        viewModel.playingMovies.observe(viewLifecycleOwner) {
            when(it.status){
                StatusEnum.LOADING -> {
                    showLoading(true)
                }
                StatusEnum.SUCCESS -> {
                    it.data?.results?.let { list ->
                        showPlayingMovies(list)
                    }
                    showLoading(false)
                }
                StatusEnum.ERROR -> {
                    ResponseDialog.showDialog(requireActivity(), App.instance.getString(R.string.get_movies_error))
                    showLoading(false)
                }
            }
        }
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            when(it.status){
                StatusEnum.LOADING -> {
                    showLoading(true)
                }
                StatusEnum.SUCCESS -> {
                    it.data?.results?.let { list ->
                        showPopularMovies(list)
                    }
                    showLoading(false)
                }
                StatusEnum.ERROR -> {
                    showLoading(false)
                }
            }
        }
        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            when(it.status){
                StatusEnum.LOADING -> {
                    showLoading(true)
                }
                StatusEnum.SUCCESS -> {
                    it.data?.results?.let { list ->
                        showTopRatedMovies(list)
                    }
                    showLoading(false)
                }
                StatusEnum.ERROR -> {
                    showLoading(false)
                }
            }
        }
    }

    override fun initView() {
        bind.apply {

        }
    }
}