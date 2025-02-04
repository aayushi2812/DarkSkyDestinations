package com.example.darkskydestinations.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.darkskydestinations.Models.FavoritePlaces

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(place: FavoritePlaces)

    @Update
    suspend fun update(place: FavoritePlaces)

    @Delete
    suspend fun delete(place: FavoritePlaces)

    @Query("SELECT * FROM places")
    fun getAllPlaces(): LiveData<List<FavoritePlaces>>
}