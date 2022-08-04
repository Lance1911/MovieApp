package com.example.filmviewer.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmviewer.model.*

class DashboardViewModel : ViewModel() {

    val TVOnAirList: MutableLiveData<List<TVResult>> = MutableLiveData()

    fun setTVOnAirList(tvList: List<TVResult>) {
        this.TVOnAirList.value = tvList
    }
}