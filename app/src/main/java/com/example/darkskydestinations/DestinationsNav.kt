package com.example.darkskydestinations

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.darkskydestinations.Models.Event
import com.example.darkskydestinations.Models.Place
import com.example.darkskydestinations.ViewModels.ApodViewModel
import com.example.darkskydestinations.ViewModels.MoonCycleViewModel
import com.example.darkskydestinations.ViewModels.PlacesViewModel
import com.example.darkskydestinations.Views.ApodScreen
import com.example.darkskydestinations.Views.EventsDetailsScreen
import com.example.darkskydestinations.Views.EventsScreen
import com.example.darkskydestinations.Views.FavoritesScreen
import com.example.darkskydestinations.Views.HomeScreen
import com.example.darkskydestinations.Views.LandingPage
import com.example.darkskydestinations.Views.MoonCyclesScreen
import com.example.darkskydestinations.Views.PlacesDetailsScreen
import com.example.darkskydestinations.Views.PlacesScreen
import com.google.gson.Gson


//Composable to handle all the navigations
@Composable
fun DestinationsNav(
    list: SnapshotStateList<Event>,
    navHostController: NavHostController,
    placesViewModel: PlacesViewModel,
    moonCyclesViewModel: MoonCycleViewModel,
    apodViewModel: ApodViewModel
){
    val gson = Gson()

    NavHost(navHostController, startDestination = "landingPage"){
        composable(route = "landingPage") {
            LandingPage(
                toHomeScreen = {
                    navHostController.navigate("homeScreen")
                }
            )
        }
        composable(route = "homeScreen") {
            HomeScreen(
                toEventsScreen = {
                    navHostController.navigate("events")
                },
                toPlacesScreen = {
                    navHostController.navigate("places")
                },
                toMoonCyclesScreen = {
                    navHostController.navigate("moonCycles")
                },
                toFavoritesScreen = {
                    navHostController.navigate("favorites")
                },
                toApodScreen = {
                    navHostController.navigate("apod")
                }
            )
        }

        composable(route = "events") {
            EventsScreen(list, toEventDetailsScreen = { event ->
                val eventJson = Uri.encode(gson.toJson(event)) // Encode the JSON to make it URL-safe
                navHostController.navigate("event/$eventJson")
            })
        }
        composable(route = "event/{eventJson}") { backStackEntry ->
            val eventJson = backStackEntry.arguments?.getString("eventJson")
            val event = gson.fromJson(eventJson, Event::class.java) // Deserialize the event JSON
            EventsDetailsScreen(event)
        }


        composable(route = "places") {
            PlacesScreen(placesViewModel, toPlaceDetailsScreen = { place ->
                val placeJson = Uri.encode(gson.toJson(place))
                navHostController.navigate("place/$placeJson")
            })
        }

        composable(route = "place/{placeJson}") { backStackEntry ->
            val placeJson = backStackEntry.arguments?.getString("placeJson")
            val place = gson.fromJson(placeJson, Place::class.java) // Deserialize the JSON
            PlacesDetailsScreen(place)
        }

        composable(route = "moonCycles") {
            MoonCyclesScreen(moonCyclesViewModel)
        }

        composable(route = "favorites") {
            FavoritesScreen(placesViewModel, toPlaceDetailsScreen = { place ->
                val placeJson = Uri.encode(gson.toJson(place))
                navHostController.navigate("place/$placeJson")
            })
        }

        composable(route = "apod") {
            ApodScreen(apodViewModel)
        }
    }
}