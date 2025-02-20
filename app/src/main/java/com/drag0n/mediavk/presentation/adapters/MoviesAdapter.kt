package com.drag0n.mediavk.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drag0n.mediavk.R
import com.drag0n.mediavk.databinding.ItemMovieBinding
import com.drag0n.mediavk.domain.model.Movie
import com.drag0n.mediavk.domain.model.MovieItem

class MoviesAdapter(private val listMovies : Movie, private val onClick: OnClick) : RecyclerView.Adapter<MoviesAdapter.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemMovieBinding.bind(view)
        private val context = view.context

        fun bind(movie: MovieItem, onClick: OnClick) = with(binding){
            itemTvName.text = movie.title
            itemTvDuration.text = movie.duration
            Glide.with(context).load(movie.thumbnailUrl).into(itemIvPoster)
            binding.root.setOnClickListener {onClick.onClik(movie)}

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listMovies[position], onClick)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    interface OnClick{
        fun onClik(item: MovieItem)

    }
}