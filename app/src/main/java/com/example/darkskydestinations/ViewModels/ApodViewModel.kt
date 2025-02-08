package com.example.darkskydestinations.ViewModels

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.darkskydestinations.Models.ApodResponse
import com.example.darkskydestinations.Networking.ApodInterface
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate


class ApodViewModel(var appRepo: AppRepository) : ViewModel() {

    //Fetches picture of the day from api data and returns it to the view

    private val _apod = mutableStateOf<ApodResponse?>(null)
    val apod: State<ApodResponse?> = _apod

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ApodInterface::class.java)

    init {
        fetchPictureOfTheDay()
    }

    @SuppressLint("NewApi")
    private fun fetchPictureOfTheDay() {
        val currentDate = LocalDate.now().toString()
        viewModelScope.launch {
            try {
                val response = api.getPictureOfTheDay("Jewu6DihdYiXt5Sq1QqhoT54LkbtrxdpWfaUzkr7",currentDate)
                _apod.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
