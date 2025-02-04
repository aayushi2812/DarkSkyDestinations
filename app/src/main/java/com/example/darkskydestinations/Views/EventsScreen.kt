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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.darkskydestinations.Models.Event
import com.example.darkskydestinations.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(list: SnapshotStateList<Event>, toEventDetailsScreen: (Event) -> Unit) {

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
                    text = "Upcoming Events",
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier.fillMaxWidth().padding(30.dp),
                    textAlign = TextAlign.Center
                )
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(count = list.toList().count()) { index: Int ->

                        Column() {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable {
                                        toEventDetailsScreen(list.toList()[index])
                                    },

                                ){
                                Text(
                                    text = list.toList()[index].title,
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                                )
                                Text(
                                    text = list.toList()[index].date,
                                    color = Color.Black,
                                    fontSize = 17.sp,
                                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}