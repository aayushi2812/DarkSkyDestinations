package com.example.darkskydestinations.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.darkskydestinations.Models.AstronomyObject
import com.example.darkskydestinations.Models.PlacesObject
import com.example.darkskydestinations.Networking.RetrofitClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class MoonCycleViewModel(var appRepo: AppRepository) : ViewModel() {

    //Fetches mooncycles api data and returns it to the view

    var astronomyResponse by mutableStateOf<Response<AstronomyObject>?>(null)

    private val apiKey = "8739787e80444e03b83153224253001"

    fun getMoonCycles(q: String, dt: String){
        viewModelScope.launch {
            val wo = appRepo.getMoonCycles(apiKey, q, dt)
            astronomyResponse = wo
        }
    }
}
