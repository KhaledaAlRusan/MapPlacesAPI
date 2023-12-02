package com.example.assignment3_hebaalsayyed_301357388.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "formatted_address")
    val formattedAddress: String,

    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "city")
    val city: String

)
