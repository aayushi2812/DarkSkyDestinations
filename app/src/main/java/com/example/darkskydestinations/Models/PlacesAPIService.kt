package com.example.darkskydestinations.Models

import com.example.darkskydestinations.Networking.RetrofitClass
import retrofit2.Response

class PlacesAPIService {
    private val apiService = RetrofitClass.placeRetrofitObject

    suspend fun fetchPlaceDetails(place: String, radius: Int): Response<PlacesObject> {
        val query = "stargazing near $place"
        return apiService.getPlaceDetails(query, radius)
    }


}