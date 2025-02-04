package com.example.darkskydestinations.Views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.darkskydestinations.Models.FavoritePlaces
import com.example.darkskydestinations.Models.Place
import com.example.darkskydestinations.R
import com.example.darkskydestinations.ViewModels.PlacesViewModel
import com.google.gson.Gson

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlacesScreen(viewModel: PlacesViewModel, toPlaceDetailsScreen: (Place) -> Unit ){

    var query by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<Place>>(emptyList()) }

    val onSearchClicked = {
        if (query.isNotBlank()) {
            viewModel.getPlacesNearCity(query, 1000)
        }
    }

    LaunchedEffect(viewModel.apiPlacesObject) {
        val response = viewModel.apiPlacesObject?.body()
        System.out.println("Response"+ response)
        searchResults = response?.results ?: emptyList()
    }

    val onFavoriteToggle: (Place) -> Unit = { place ->
        val favoritePlace = place.toFavoritePlace() // Convert Place to FavoritePlaces
        val updatedFavoritePlace = favoritePlace.copy(isFavorite = !favoritePlace.isFavorite) // Toggle the favorite status
        viewModel.saveFavoritePlace(updatedFavoritePlace) // Save the updated FavoritePlace to Room
        System.out.println("69:"+viewModel.favoritePlaces)
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

            // Search button
            Button(
                onClick = onSearchClicked,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                )
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(searchResults) { place ->
                    PlaceItem(place, toPlaceDetailsScreen, onFavoriteToggle)
                }
            }
        }
    }
}


@Composable
fun PlaceItem(place: Place, toPlaceDetailsScreen: (Place) -> Unit={}, onFavoriteToggle: (Place) -> Unit={}) {

    val isFavorite = remember { mutableStateOf(place.toFavoritePlace().isFavorite) }
    val favoritePlace = place.toFavoritePlace()
    Card(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                toPlaceDetailsScreen(place)
            },

        ) {
        Row {
            Column(modifier = Modifier.padding(16.dp).width(300.dp)) {
                Text(text = place.name, fontSize = 18.sp,
                    color = Color.Black
                    , modifier = Modifier.padding(2.dp))
                Text(
                    text = place.formatted_address,
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(2.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(onClick = {
                // Toggle the favorite status
                isFavorite.value = !isFavorite.value
                onFavoriteToggle(place) // Trigger the external function
            }) {
                val icon = if (isFavorite.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
                Icon(
                    imageVector = icon,
                    contentDescription = "Favorite",
                    tint = if (isFavorite.value) Color.Red else Color.Gray // Red for favorite, gray for not favorite
                )
            }
        }
    }
}
fun Place.toFavoritePlace(): FavoritePlaces {
    return FavoritePlaces(
        place_id = this.place_id,
        business_status = this.business_status,
        formatted_address = this.formatted_address,
        icon = this.icon,
        icon_background_color = this.icon_background_color,
        icon_mask_base_uri = this.icon_mask_base_uri,
        name = this.name,
        rating = this.rating,
        reference = this.reference,
        user_ratings_total = this.user_ratings_total,
        isFavorite = false // Default value for isFavorite, you can set it dynamically later
    )
}

//
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

   val json = """
    {
        "business_status": "OPERATIONAL",
        "formatted_address": "Southwood Rd, Gravenhurst, ON P0C 1M0, Canada",
        "geometry": {
            "location": {
                "lat": 44.9414869,
                "lng": -79.51335399999999
            },
            "viewport": {
                "northeast": {
                    "lat": 44.94275602989272,
                    "lng": -79.51207207010728
                },
                "southwest": {
                    "lat": 44.94005637010728,
                    "lng": -79.51477172989271
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/park-71.png",
        "icon_background_color": "#4DB546",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/tree_pinlet",
        "name": "Torrance Barrens Dark-Sky Preserve",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 8083,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/113357502892030375524\">Dake Liu</a>"
                ],
                "photo_reference": "AVzFdbmnV9z_RfXEWihtpXmBJO_60TNNB82_WJxOBMU_pAfpI_cJgHuAv80e0cy7q70-d8dojJnhU176cUcPi8RWKFA1PCF4rS4KlHow7uhM97vJ4e5r4L4mYsnN_QgniL0tP-WiIHv7-LvccC6f1X5qA6iJEyWT2PJ-attcWI_1dr--QZze",
                "width": 12333
            }
        ],
        "place_id": "ChIJS8zqrDuSKk0R_r108fo4nf0",
        "plus_code": {
            "compound_code": "WFRP+HM Gravenhurst, Ontario, Canada",
            "global_code": "87P2WFRP+HM"
        },
        "rating": 4.6,
        "reference": "ChIJS8zqrDuSKk0R_r108fo4nf0",
        "types": [
            "tourist_attraction",
            "park",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 2550
    }
    """

    val gson = Gson()
    val place = gson.fromJson(json, Place::class.java)

    PlaceItem(
        place
    )
}