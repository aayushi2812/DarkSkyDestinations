package com.example.darkskydestinations.ViewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.darkskydestinations.Models.FavoritePlaces
import com.example.darkskydestinations.Models.Place
import com.example.darkskydestinations.Models.PlacesObject
import com.example.darkskydestinations.Room.AppDatabase
import com.example.darkskydestinations.Room.PlaceDao
import kotlinx.coroutines.launch
import retrofit2.Response

class PlacesViewModel(var appRepo: AppRepository,application: Application) : AndroidViewModel(application) {

    var apiPlacesObject by mutableStateOf<Response<PlacesObject>?>(null)

    fun getPlacesNearCity(name: String, i: Int){
        viewModelScope.launch {
            val wo = appRepo.getPlaceDetails(name, i)
            System.out.println("19")
            System.out.println(wo.body())
            apiPlacesObject = wo
        }
    }
    private val placeDao: PlaceDao = AppDatabase.getDatabase(application).placeDao()

    val favoritePlaces: LiveData<List<FavoritePlaces>> = placeDao.getAllPlaces()

    fun deleteFavoritePlace(favoritePlace: FavoritePlaces) {
        viewModelScope.launch {
            placeDao.delete(favoritePlace) // Insert or update the favorite place in the database
            System.out.println("deleted favorite place:"+ favoritePlace)
        }
    }

    fun saveFavoritePlace(favoritePlace: FavoritePlaces) {
        viewModelScope.launch {
            placeDao.insert(favoritePlace) // Insert or update the favorite place in the database
            System.out.println("favorite place:"+ favoritePlace)
        }
    }
}
