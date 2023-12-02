package com.example.assignment3_hebaalsayyed_301357388.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignment3_hebaalsayyed_301357388.data.PlacesDao
import com.example.assignment3_hebaalsayyed_301357388.domain.model.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placesDao(): PlacesDao
}
