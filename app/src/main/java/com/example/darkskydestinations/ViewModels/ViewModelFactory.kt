package com.example.darkskydestinations.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val appRepo: AppRepository, private val application: Application) : ViewModelProvider.Factory {

    //Creating factory for the three view model classes
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlacesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlacesViewModel(appRepo,application) as T
        }else if(modelClass.isAssignableFrom(MoonCycleViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MoonCycleViewModel(appRepo) as T
        }else if(modelClass.isAssignableFrom(ApodViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ApodViewModel(appRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
