package com.example.assignment3_hebaalsayyed_301357388.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment3_hebaalsayyed_301357388.domain.model.PlaceEntity

@Dao
interface PlacesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: PlaceEntity)

    //get places by category and city
    @Query("SELECT * FROM places WHERE category = :category AND city = :city")
    suspend fun getPlacesByCategoryAndCity(category: String, city: String): List<PlaceEntity>

}
