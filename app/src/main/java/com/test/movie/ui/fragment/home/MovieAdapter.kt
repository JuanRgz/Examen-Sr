package com.test.movie.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.test.movie.core.ApiConstants
import com.test.movie.core.remote.response.MovieResponseModel
import com.test.movie.databinding.ItemMovieBinding

class MovieAdapter(private val list: List<MovieResponseModel>, private val onClick: (msg: String, date: String) -> Unit): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val bind = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(bind)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class MovieViewHolder(private val bind: ItemMovieBinding): RecyclerView.ViewHolder(bind.root){
        fun bind(itemMovie: MovieResponseModel) {
            bind.apply {
                //Agregando imagen desde Glide
                Glide.with(root.context)
                    .load(ApiConstants.PATH_IMG.plus(itemMovie.poster_path))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image)

                layout.setOnClickListener{
                    onClick(itemMovie.title, itemMovie.release_date)
                }
            }
        }
    }
}