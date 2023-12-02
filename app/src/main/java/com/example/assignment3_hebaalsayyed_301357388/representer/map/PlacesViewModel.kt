package com.example.assignment3_hebaalsayyed_301357388.representer.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment3_hebaalsayyed_301357388.domain.model.PlaceEntity
import com.example.assignment3_hebaalsayyed_301357388.domain.model.PlacesApiResponse
import com.example.assignment3_hebaalsayyed_301357388.domain.repo.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(private val repository: PlacesRepository) : ViewModel() {
    private val _placesData = MutableLiveData<PlacesApiResponse>()
    val placesData: LiveData<PlacesApiResponse> = _placesData

    private val _placesFromDB = MutableLiveData<List<PlaceEntity>>()
    val placesFromDB: LiveData<List<PlaceEntity>> = _placesFromDB

    fun fetchPlaces(city: String, category: String) {
        val query = "$category near $city"
        val apiKey = "AIzaSyD8ys_MCMj3eQ7i9DdvEOKvIsrOMjDAe3U"

        viewModelScope.launch {
            try {
                val places = repository.getPlaces(query,apiKey)
                _placesData.postValue(places)
                savePlacesData(places,category,city)
            } catch (e: Exception) {
                Log.d("PlacesViewModel", e.message.toString())
            }
        }
    }

    fun fetchPlacesByCategory(category: String,city:String) {
        viewModelScope.launch {
            try {
                val savedPlaces = repository.getPlacesByCategoryAndCity(category,city)
                _placesFromDB.postValue(savedPlaces)
            } catch (e: Exception) {
                Log.d("PlacesViewModel", "Error fetching places by category: ${e.message}")
            }
        }
    }


    private fun savePlacesData(placesApiResponse: PlacesApiResponse,category: String,city:String) {
        viewModelScope.launch {
            placesApiResponse.results.forEach { placeResult ->
                val placeEntity = PlaceEntity(
                    name = placeResult.name,
                    latitude = placeResult.geometry.location.lat,
                    longitude = placeResult.geometry.location.lng,
                    formattedAddress = placeResult.formatted_address,
                    category = category,
                    city = city
                )
                repository.savePlace(placeEntity)
            }
        }
    }
}
