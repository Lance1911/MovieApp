package com.example.filmviewer.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmviewer.R
import com.example.filmviewer.databinding.FragmentDashboardBinding
import com.example.filmviewer.model.NowPlayingList
import com.example.filmviewer.model.TVAiringList
import com.example.filmviewer.model.TVResult
import com.example.filmviewer.ui.home.MovieDatabaseAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.filmviewer.ui.home.*

class DashboardFragment : Fragment(), TVAdapter.onTVClickListener {

    private val dashboardViewModel: DashboardViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTVShowList()

        dashboardViewModel.TVOnAirList.observe(viewLifecycleOwner, {
            binding.recyclerView.adapter = TVAdapter(it as ArrayList<TVResult>, this)
            binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
            binding.recyclerView.setHasFixedSize(true)
        })
    }

    override fun onTVClickListener(position: Int) {
        super.onTVClickListener(position)
    }

    private fun getTVShowList() {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/").addConverterFactory(
            GsonConverterFactory.create()).build()

        var movieDatabaseAPI = retrofit.create(MovieDatabaseAPI::class.java)
        var call = movieDatabaseAPI.getTVOnAirToday()

        call.enqueue(object: Callback<TVAiringList> {
            override fun onResponse(call: Call<TVAiringList>, response: Response<TVAiringList>) {
                response.body()?.let { dashboardViewModel.setTVOnAirList(it.results) }

            }

            override fun onFailure(call: Call<TVAiringList>, t: Throwable) {
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}