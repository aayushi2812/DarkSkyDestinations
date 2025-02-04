package com.example.darkskydestinations

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.darkskydestinations.ViewModels.AppRepository
import com.example.darkskydestinations.ViewModels.EventViewModel
import com.example.darkskydestinations.ViewModels.MoonCycleViewModel
import com.example.darkskydestinations.ViewModels.PlacesViewModel
import com.example.darkskydestinations.ViewModels.ViewModelFactory
import com.example.darkskydestinations.ui.theme.DarkSkyDestinationsTheme

class MainActivity : ComponentActivity() {

    lateinit var vm : Lazy<EventViewModel>

    val repository = AppRepository() // Ensure you have an instance of AppRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DarkSkyDestinationsTheme {
                val factory = ViewModelFactory(repository,application)
                vm = viewModels<EventViewModel>()
                val placesViewModel: PlacesViewModel = ViewModelProvider(this, factory).get(PlacesViewModel::class.java)
                val moonCyclesViewModel: MoonCycleViewModel = ViewModelProvider(this, factory).get(MoonCycleViewModel::class.java)
                //var temp = placesViewModel.getPlacesNearCity("Toronto", 1000)

                //System.out.println("Line 34"+ temp)

                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize().background(Color.Black)) { innerPadding ->
                    System.out.println(vm.value.stateList);
                    DestinationsNav(vm.value.stateList,navController, placesViewModel, moonCyclesViewModel)
                }
            }
        }
    }
}



