package com.example.filmviewer.ui.dashboard

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmviewer.R
import com.example.filmviewer.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tv_card.view.*

class TVAdapter(private val recyclerList: ArrayList<TVResult>, private val listener: onTVClickListener):
    RecyclerView.Adapter<TVAdapter.RecyclerViewHolder>() {
    inner class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val poster: ImageView = view.poster
        val tvShowName: TextView = view.showTitle
        val rating: TextView = view.rating

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            var position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onTVClickListener(position)
            }
        }

    }

    interface onTVClickListener {
        fun onTVClickListener(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tv_card, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentTVShow = recyclerList[position]
        holder.tvShowName.text = currentTVShow.name
        val imageURL = "http://image.tmdb.org/t/p/w500"

        val bldr: StringBuilder = StringBuilder("Rating: ")
        bldr.append(currentTVShow.vote_average.toString())
        holder.rating.text = bldr.toString()
        Log.e("DEBUG", imageURL + currentTVShow.poster_path)
        Picasso.get().load("http://image.tmdb.org/t/p/w500"
                + currentTVShow.poster_path).resize(300, 350).into(holder.poster)
    }

    override fun getItemCount(): Int {
        return this.recyclerList.size
    }
}