package com.example.darkskydestinations.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClass {

    private const val BASE_URL = "https://google-map-places.p.rapidapi.com/"

    private const val MOON_BASE_URL = "https://api.weatherapi.com/v1/"

    val placeRetrofitObject: PlacesAPIInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlacesAPIInterface::class.java)
    }

    val moonCycleRetrofitObject: MoonCyclesAPIInterface by lazy {
        Retrofit.Builder()
            .baseUrl(MOON_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoonCyclesAPIInterface::class.java)
    }

}