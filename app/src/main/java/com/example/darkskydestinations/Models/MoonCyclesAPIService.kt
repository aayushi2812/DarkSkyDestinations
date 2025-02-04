package com.example.darkskydestinations.Models

import com.example.darkskydestinations.Networking.RetrofitClass
import retrofit2.Response

class MoonCyclesAPIService {
    private val apiService = RetrofitClass.moonCycleRetrofitObject

    suspend fun getMoonCycles(key: String, q: String, dt: String): Response<AstronomyObject> {
        return apiService.getAstronomyData(key, q, dt)
    }
}