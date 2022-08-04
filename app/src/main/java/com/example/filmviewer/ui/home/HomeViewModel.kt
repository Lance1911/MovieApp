package com.example.filmviewer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmviewer.model.*
import retrofit2.Call

class HomeViewModel : ViewModel() {

    var nowPlayingList: MutableLiveData<List<MovieResult>> = MutableLiveData()

    private val chosenMovie: MutableLiveData<MovieResult> = MutableLiveData()

    fun setNowPlayingList(npl: List<MovieResult>) {
        this.nowPlayingList.value = npl
    }

    fun setChosenMovie(chosenMovie: MovieResult) {
        this.chosenMovie.value = chosenMovie
    }

    fun getChosenMovie(): MovieResult? {
        return this.chosenMovie.value
    }
}