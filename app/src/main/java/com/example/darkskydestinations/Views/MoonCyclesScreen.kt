package com.example.darkskydestinations.Views

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.darkskydestinations.Models.Astronomy
import com.example.darkskydestinations.R
import com.example.darkskydestinations.ViewModels.MoonCycleViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoonCyclesScreen(moonCyclesViewModel: MoonCycleViewModel) {

    //MoonCyclesScreen fetches moon cycles from the api and displays it here with the image

    var query by remember { mutableStateOf("") }
    var show by remember { mutableStateOf(false) }
    var searchResults by remember { mutableStateOf<Astronomy?>(null) }
    val calendar = Calendar.getInstance()
    val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
    var selectedDate by remember { mutableStateOf(currentDate) }
    val context = LocalContext.current

    val onSearchClicked = {
        if (query.isNotBlank()) {
            show = true
            moonCyclesViewModel.getMoonCycles(query, selectedDate)
        }
    }

    // DatePickerDialog
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            // Convert selected date to a specific format
            val date = Calendar.getInstance()
            date.set(year, month, dayOfMonth)
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            selectedDate = formatter.format(date.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    LaunchedEffect(moonCyclesViewModel.astronomyResponse) {
        // Update searchResults when API response is available
        val response = moonCyclesViewModel.astronomyResponse?.body()
        searchResults = (response?.astronomy) as Astronomy?

    }

    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.backgroundimage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            // Search bar
            TextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search Location") },
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { datePickerDialog.show() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
            ) {
                Text("Pick a Date")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onSearchClicked,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(16.dp))
            if(show){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    if(searchResults?.astro?.moonPhase == "First Quarter"){
                        Image(
                            painter = painterResource(id = R.drawable.lastquarter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .width(300.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    }else if(searchResults?.astro?.moonPhase == "Full Moon"){
                        Image(
                            painter = painterResource(id = R.drawable.fullmoon),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .width(300.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    }else if(searchResults?.astro?.moonPhase == "Last Quarter"){
                        Image(
                            painter = painterResource(id = R.drawable.lastquarter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .width(300.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    }else if(searchResults?.astro?.moonPhase == "New Moon"){
                        Image(
                            painter = painterResource(id = R.drawable.newmoon),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .width(300.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    }else if(searchResults?.astro?.moonPhase == "Waning Crescent"){
                        Image(
                            painter = painterResource(id = R.drawable.waningcrescent),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .width(300.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    }else if(searchResults?.astro?.moonPhase == "Waning Gibbous"){
                        Image(
                            painter = painterResource(id = R.drawable.waninggibbous),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .width(300.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    }else if(searchResults?.astro?.moonPhase == "Waxing Crescent"){
                        Image(
                            painter = painterResource(id = R.drawable.waxingcrescent),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .width(300.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    }else if(searchResults?.astro?.moonPhase == "Waxing Gibbous"){
                        Image(
                            painter = painterResource(id = R.drawable.waxinggibbous),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .width(300.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Sun Rise: " + (searchResults?.astro?.sunrise ),
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
                Text(
                    text = "Sun Set: " + (searchResults?.astro?.sunset ),
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
                Text(
                    text = "Moon Rise: " + (searchResults?.astro?.moonrise ),
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
                Text(
                    text = "Moon Set: " + (searchResults?.astro?.moonset ),
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
                Text(
                    text = "Moon Phase: " + (searchResults?.astro?.moonPhase ),
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
                if(searchResults?.astro?.isMoonUp == 1){
                    Text(
                        text = "Moon Up: Yes",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                    )
                }else{
                    Text(
                        text = "Moon Up: No",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                    )
                }
                if(searchResults?.astro?.isSunUp == 1){
                    Text(
                        text = "Sun Up: Yes",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                    )
                }else{
                    Text(
                        text = "Sun Up: No",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                    )
                }
            }

        }
    }
}