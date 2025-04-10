package com.development.cursoandroid.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.development.cursoandroid.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    lateinit var bind: FragmentMovieDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind = FragmentMovieDetailBinding.bind(view)
    }
}