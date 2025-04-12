package com.development.cursoandroid.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.development.cursoandroid.R
import com.development.cursoandroid.application.ApiConstants
import com.development.cursoandroid.databinding.FragmentMovieDetailBinding
import com.development.cursoandroid.ui.tools.getImageUrl

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    lateinit var bind: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind = FragmentMovieDetailBinding.bind(view)
        Glide.with(requireContext()).load(args.posterImageUrl.getImageUrl()).into(bind.imgMovie)
        Glide.with(requireContext()).load(args.backdropImageUrl.getImageUrl()).into(bind.imgBackground)
        bind.txtDescription.text = args.overview
        bind.txtMovieTitle.text = args.title
        bind.txtLanguage.text = "Language: ${args.language}"
        bind.txtRating.text = "${args.voteAverage} (${args.voteCount} Reviews)"
        bind.txtReleased.text = "Released ${args.releaseDate}"
    }
}