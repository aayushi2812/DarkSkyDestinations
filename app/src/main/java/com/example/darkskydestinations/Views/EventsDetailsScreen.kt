package com.example.darkskydestinations.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.darkskydestinations.Models.Event
import com.example.darkskydestinations.Models.EventsContainer
import com.example.darkskydestinations.R
import com.example.darkskydestinations.ui.theme.DarkSkyDestinationsTheme
import com.google.gson.Gson

@Composable
fun EventsDetailsScreen(event: Event){

    //ApodScreen gets the picture of the day from NASA Api and displays it on the screen

    Scaffold (
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.backgroundimage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = event.title,
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier.fillMaxWidth().padding(30.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Date: " + event.date,
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth().padding(30.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = event.description,
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth().padding(30.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting1Preview() {

    val json =
        """
            {
                  "date": "January 1",
                  "title": "Mercury at Greatest Eastern Elongation",
                  "description": "The planet Mercury reaches greatest eastern elongation of 19.4 degrees from the Sun. Best time to view Mercury in the evening sky after sunset."
                }

        """.trimIndent()
    val gson = Gson()

    val parsedData = gson.fromJson(json, Event::class.java);

    DarkSkyDestinationsTheme {
        EventsDetailsScreen(parsedData)
    }
}