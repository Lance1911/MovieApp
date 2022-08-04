package com.example.filmviewer.ui.home

import com.example.filmviewer.model.*
import retrofit2.Call
import retrofit2.http.GET

interface MovieDatabaseAPI {

    @GET("3/movie/now_playing?api_key=b7868c3929f98729892a2e4976f8ce01&language=en-US&page=1")
    fun getNowPlaying(): Call<NowPlayingList>

    @GET("/3/tv/airing_today?api_key=b7868c3929f98729892a2e4976f8ce01&language=en-US&page=1")
    fun getTVOnAirToday(): Call<TVAiringList>
}