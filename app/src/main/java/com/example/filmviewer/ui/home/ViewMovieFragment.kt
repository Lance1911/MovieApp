package com.example.filmviewer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.filmviewer.R
import com.example.filmviewer.databinding.ViewMovieFragmentBinding
import com.squareup.picasso.Picasso

class ViewMovieFragment: Fragment(R.layout.view_movie_fragment) {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: ViewMovieFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = ViewMovieFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chosenMovie = homeViewModel.getChosenMovie()
        binding.movieName.text = chosenMovie?.title
        Picasso.get().load("https://image.tmdb.org/t/p/w500"
                + chosenMovie?.poster_path).into(binding.backdrop)

        val bldr: StringBuilder = StringBuilder("Viewer Rating: ")
        bldr.append(chosenMovie?.vote_average.toString())

        binding.viewerRating.text = bldr.toString()
        binding.synopsis.text = chosenMovie?.overview

    }
}