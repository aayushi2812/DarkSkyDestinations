package com.example.darkskydestinations.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.darkskydestinations.Models.Place
import com.example.darkskydestinations.R
import com.google.gson.Gson

@Composable
fun PlacesDetailsScreen(place: Place){

    System.out.println("In place details: " + place)

    Scaffold (
        modifier = Modifier.fillMaxSize()
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
                    text = place.name,
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier.fillMaxWidth().padding(30.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Address: " + place.formatted_address,
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth().padding(30.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (place.opening_hours?.open_now == true){
                    Text(
                        text = "Open Now: Yes",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth().padding(30.dp),

                        )
                } else {
                    Text(
                        text = "Open Now: No",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth().padding(30.dp),

                        )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Rating: " + place.rating,
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth().padding(30.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Total User Ratings: " + place.user_ratings_total,
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth().padding(30.dp)
                )
//                Button(
//                    onClick = {},
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = ButtonDefaults.buttonColors(
//                        contentColor = Color.White,
//                        containerColor = Color.Black
//                    ),
//                ) {
//                    Text("Mark as Favorite")
//                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Default1Preview() {

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

    PlacesDetailsScreen(place)
}