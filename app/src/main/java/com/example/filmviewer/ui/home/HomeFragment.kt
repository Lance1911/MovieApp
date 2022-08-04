package com.example.filmviewer.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmviewer.MainActivity
import com.example.filmviewer.R
import com.example.filmviewer.databinding.FragmentHomeBinding
import com.example.filmviewer.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment(), MovieAdapter.OnMovieClickListener {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNowPlaying()

        val screenOrientation: Int = resources.configuration.orientation
        if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
            homeViewModel.nowPlayingList.observe(viewLifecycleOwner,  {
                binding.movieRecyclerView.adapter = MovieAdapter(it as ArrayList<MovieResult>, this)
                binding.movieRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
                binding.movieRecyclerView.setHasFixedSize(true)

            })
        }
        else if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            homeViewModel.nowPlayingList.observe(viewLifecycleOwner, {
                binding.movieRecyclerView.adapter = MovieAdapter(it as ArrayList<MovieResult>, this)
                binding.movieRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.movieRecyclerView.setHasFixedSize(true)

            })
        }



    }

    private fun getNowPlaying() {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/").addConverterFactory(
            GsonConverterFactory.create()).build()

        var movieDatabaseAPI = retrofit.create(MovieDatabaseAPI::class.java)
        var call = movieDatabaseAPI.getNowPlaying()

        call.enqueue(object: Callback<NowPlayingList> {
            override fun onResponse(call: Call<NowPlayingList>, response: Response<NowPlayingList>) {
                response.body()?.let { homeViewModel.setNowPlayingList(it.results) }
            }

            override fun onFailure(call: Call<NowPlayingList>, t: Throwable) {
            }

        })
    }

    override fun onMovieClick(position: Int) {
        homeViewModel.nowPlayingList.observe(viewLifecycleOwner, {
            homeViewModel.nowPlayingList.value?.let {
                    it1 -> homeViewModel.setChosenMovie(it1.get(position))
            }
        })

        findNavController().navigate(R.id.action_navigation_home_to_viewMovieFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}