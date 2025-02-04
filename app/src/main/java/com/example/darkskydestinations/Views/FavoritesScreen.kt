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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.darkskydestinations.Models.FavoritePlaces
import com.example.darkskydestinations.Models.Place
import com.example.darkskydestinations.R
import com.example.darkskydestinations.ViewModels.PlacesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(viewModel: PlacesViewModel,toPlaceDetailsScreen: (Place) -> Unit={}){

    var searchResults = viewModel.favoritePlaces.observeAsState(emptyList())

    val onFavoriteRemove: (Place) -> Unit = { place ->
        val favoritePlace = place.toFavoritePlace() // Convert Place to FavoritePlaces
        System.out.println("isfavorite" + favoritePlace.isFavorite)
//        val updatedFavoritePlace = favoritePlace.copy(isFavorite = favoritePlace.isFavorite) // Toggle the favorite status
        viewModel.deleteFavoritePlace(favoritePlace) // Save the updated FavoritePlace to Room
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
            Text(
                text = "Favorite Places",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth().padding(30.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(searchResults.value){ item ->
                    FavoritePlaceItem(item,toPlaceDetailsScreen, onFavoriteRemove)
                }
            }
        }
    }
}

@Composable
fun FavoritePlaceItem(fplace: FavoritePlaces,toPlaceDetailsScreen: (Place) -> Unit={},onFavoriteRemove: (Place) -> Unit={}){
    val isFavorite = remember { mutableStateOf(fplace.isFavorite) }

    Card(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val place = fplace.toPlace()
                toPlaceDetailsScreen(place)
            },

        ) {
        Row {
            Column(modifier = Modifier.padding(16.dp).width(300.dp)) {
                Text(text = fplace.name, fontSize = 18.sp,
                    color = Color.Black
                    , modifier = Modifier.padding(2.dp))
                Text(
                    text = fplace.formatted_address,
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
                onFavoriteRemove(fplace.toPlace()) // Trigger the external function
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

fun FavoritePlaces.toPlace(): Place {
    return Place(
        place_id = this.place_id,
        business_status = this.business_status,
        formatted_address = this.formatted_address,
        geometry = null,
        icon = this.icon,
        icon_background_color = this.icon_background_color,
        icon_mask_base_uri = this.icon_mask_base_uri,
        name = this.name,
        opening_hours = null,
        photos = null,
        plus_code = null,
        rating = this.rating,
        reference = this.reference,
        types = null,
        user_ratings_total = this.user_ratings_total
    )
}