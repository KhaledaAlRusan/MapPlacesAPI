package com.example.assignment3_hebaalsayyed_301357388.domain.repo

import com.example.assignment3_hebaalsayyed_301357388.data.PlacesAPIService
import com.example.assignment3_hebaalsayyed_301357388.data.PlacesDao
import com.example.assignment3_hebaalsayyed_301357388.domain.model.PlaceEntity
import javax.inject.Inject

class PlacesRepository @Inject constructor(
    private val placesAPIService: PlacesAPIService,
    private val placesDao: PlacesDao
) {
    suspend fun getPlaces(query: String, apiKey: String) = placesAPIService.getPlaces(query, apiKey)

    suspend fun savePlace(placeEntity: PlaceEntity) {
        placesDao.insertPlace(placeEntity)
    }

    suspend fun getPlacesByCategoryAndCity(category: String, city:String): List<PlaceEntity> {
        return placesDao.getPlacesByCategoryAndCity(category,city)
    }

}