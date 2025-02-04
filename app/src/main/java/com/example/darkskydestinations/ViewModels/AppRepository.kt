package com.example.darkskydestinations.ViewModels

import com.example.darkskydestinations.Models.AstronomyObject
import com.example.darkskydestinations.Models.Event
import com.example.darkskydestinations.Models.EventsInterface
import com.example.darkskydestinations.Models.EventsService
import com.example.darkskydestinations.Models.MoonCyclesAPIService
import com.example.darkskydestinations.Models.PlacesAPIService
import com.example.darkskydestinations.Models.PlacesObject
import com.example.darkskydestinations.Networking.PlacesAPIInterface
import retrofit2.Response

class AppRepository : EventsInterface, PlacesAPIInterface{

    var staticEventService = EventsService()
    var places = PlacesAPIService()
    var moonCycles = MoonCyclesAPIService()


    override fun getInitialEventsList(): ArrayList<Event> {
        return staticEventService.initList();
    }

    override suspend fun getPlaceDetails(place: String, radius: Int): Response<PlacesObject> {
        return places.fetchPlaceDetails(place, radius);
    }

    suspend fun getMoonCycles(key: String, q: String, dt: String): Response<AstronomyObject> {
        return moonCycles.getMoonCycles(key, q, dt);
    }

}