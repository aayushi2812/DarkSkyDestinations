package com.example.darkskydestinations.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class PlacesObject(
    val html_attributions: List<String>,
    val results: List<Place>,
    val status: String
)

data class Place(
    val business_status: String,
    val formatted_address: String,
    val geometry: Geometry?,
    val icon: String,
    val icon_background_color: String,
    val icon_mask_base_uri: String,
    val name: String,
    val opening_hours: OpeningHours?,
    val photos: List<Photo>?,
    val place_id: String,
    val plus_code: PlusCode?,
    val rating: Double,
    val reference: String,
    val types: List<String>?,
    val user_ratings_total: Int
)

@Entity(tableName = "places")
data class FavoritePlaces(
    @PrimaryKey val place_id: String,
    val business_status: String,
    val formatted_address: String,
    val icon: String,
    val icon_background_color: String,
    val icon_mask_base_uri: String,
    val name: String,
    val rating: Double,
    val reference: String,
    val user_ratings_total: Int,
    var isFavorite: Boolean = false
)

data class Geometry(
    val location: Location,
    val viewport: Viewport
)

data class Location(
    val lat: Double,
    val lng: Double
)

data class Viewport(
    val northeast: Location,
    val southwest: Location
)

data class OpeningHours(
    val open_now: Boolean
)

data class Photo(
    val height: Int,
    val html_attributions: List<String>,
    val photo_reference: String,
    val width: Int
)

data class PlusCode(
    val compound_code: String,
    val global_code: String
)
