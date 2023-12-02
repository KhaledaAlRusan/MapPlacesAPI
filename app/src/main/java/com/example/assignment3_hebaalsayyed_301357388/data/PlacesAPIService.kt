package com.example.assignment3_hebaalsayyed_301357388.data

import com.example.assignment3_hebaalsayyed_301357388.domain.model.PlacesApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface PlacesAPIService {
    //get query from user and return list of places
    @GET("api/place/textsearch/json")
    suspend fun getPlaces(@Query("query") query: String, @Query("key") apiKey: String): PlacesApiResponse
}