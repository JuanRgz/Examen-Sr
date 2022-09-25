package com.test.movie.ui.fragment.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.test.movie.core.location.ImageName
import com.test.movie.databinding.ItemMovieBinding

class ImageAdapter(private val list: List<ImageName>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val bind = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ImageViewHolder(private val bind: ItemMovieBinding): RecyclerView.ViewHolder(bind.root){
        fun bind(itemMovie: ImageName) {
            bind.apply {
                //Agregando imagen desde Glide
                Glide.with(root.context)
                    .load(itemMovie.url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image)
            }
        }
    }
}