package com.example.darkskydestinations.Views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.darkskydestinations.R


//Home screen shows the buttons that navigate to different screens
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    toMoonCyclesScreen: () -> Unit,
    toPlacesScreen: () -> Unit,
    toEventsScreen: () -> Unit,
    toFavoritesScreen: () -> Unit,
    toApodScreen: () -> Unit
) {
    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.backgroundimage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Explore the",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "World of Stargazing",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {toEventsScreen()},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .height(56.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(16.dp))) {
                Text(
                    text = "Astronomical Events",
                    fontSize = 17.sp,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {toPlacesScreen()},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .height(56.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(16.dp))
                ) {
                Text(
                    text = "Nearby Destinations",
                    fontSize = 17.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {toMoonCyclesScreen()},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .height(56.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(16.dp))) {
                Text(
                    text = "Moon Cycles",
                    fontSize = 17.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {toFavoritesScreen()},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .height(56.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(16.dp))) {
                Text(
                    text = "Favorite Places",
                    fontSize = 17.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {toApodScreen()},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .height(56.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(16.dp))) {
                Text(
                    text = "Astronomy Picture of the Day",
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
