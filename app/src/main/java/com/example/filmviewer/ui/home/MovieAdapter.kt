package com.example.filmviewer.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmviewer.R
import com.example.filmviewer.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_card.view.*

class MovieAdapter(private val recyclerList: ArrayList<MovieResult>,
                   private val listener: OnMovieClickListener):
    RecyclerView.Adapter<MovieAdapter.RecyclerViewHolder>() {

    interface OnMovieClickListener {
        fun onMovieClick(position: Int) {

        }
    }

    inner class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val poster: ImageView = view.poster
        val movieName: TextView = view.movieTitle
        val rating: TextView = view.rating

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            var position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onMovieClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentMovieItem = recyclerList[position]
        val imageURL = "http://image.tmdb.org/t/p/w500"

        if (currentMovieItem != null) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + currentMovieItem.poster_path).resize(300, 350).into(holder.poster)
            holder.movieName.text = currentMovieItem.title
            holder.rating.text = "Rating: " + currentMovieItem.vote_average
        }
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }
}