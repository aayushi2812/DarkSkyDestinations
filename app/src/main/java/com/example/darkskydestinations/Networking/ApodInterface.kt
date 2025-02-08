package com.example.darkskydestinations.Networking

import com.example.darkskydestinations.Models.ApodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodInterface {
    //    Apod api configuration
        @GET("planetary/apod")
        suspend fun getPictureOfTheDay(
            @Query("api_key") apiKey: String,
            @Query("date") date: String
        ): ApodResponse
}