package com.example.darkskydestinations.Networking


import com.example.darkskydestinations.Models.PlacesObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PlacesAPIInterface {

    @Headers(
        "x-rapidapi-host: google-map-places.p.rapidapi.com",
        "x-rapidapi-key: ed3563df6fmshcefc9abf50bd9fbp1bd433jsn447263130352"
    )
    @GET("maps/api/place/textsearch/json")
    suspend fun getPlaceDetails(
        @Query("query") query: String,
        @Query("radius") radius: Int = 1000
    ): Response<PlacesObject>

}