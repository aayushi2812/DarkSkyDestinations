package com.example.darkskydestinations.Networking

import com.example.darkskydestinations.Models.AstronomyObject
import com.example.darkskydestinations.Models.PlacesObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoonCyclesAPIInterface {

    @GET("astronomy.json")
    suspend fun getAstronomyData(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("dt") date: String
    ): Response<AstronomyObject>
}