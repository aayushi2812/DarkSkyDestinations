package com.example.darkskydestinations

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.darkskydestinations.ViewModels.ApodViewModel
import com.example.darkskydestinations.ViewModels.AppRepository
import com.example.darkskydestinations.ViewModels.EventViewModel
import com.example.darkskydestinations.ViewModels.MoonCycleViewModel
import com.example.darkskydestinations.ViewModels.PlacesViewModel
import com.example.darkskydestinations.ViewModels.ViewModelFactory
import com.example.darkskydestinations.ui.theme.DarkSkyDestinationsTheme

class MainActivity : ComponentActivity() {

    lateinit var vm : Lazy<EventViewModel>
    //App repository
    val repository = AppRepository()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DarkSkyDestinationsTheme {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                //ViewModelFactory
                val factory = ViewModelFactory(repository,application)
                //Initializing all the view models
                vm = viewModels<EventViewModel>()
                val placesViewModel: PlacesViewModel = ViewModelProvider(this, factory).get(PlacesViewModel::class.java)
                val moonCyclesViewModel: MoonCycleViewModel = ViewModelProvider(this, factory).get(MoonCycleViewModel::class.java)
                val apodViewModel: ApodViewModel = ViewModelProvider(this, factory).get(ApodViewModel::class.java)

                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize().background(Color.Black)) { innerPadding ->
                    //Calling destinations Nav to handle navigations
                    DestinationsNav(vm.value.stateList,navController, placesViewModel, moonCyclesViewModel, apodViewModel)
                }
            }
        }
    }
}



