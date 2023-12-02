package com.example.assignment3_hebaalsayyed_301357388.domain.model

data class PlacesApiResponse(
    val results: List<PlaceResult>,
)

data class PlaceResult(
    val name: String,
    val geometry: Geometry,
    val formatted_address: String,
)

data class Geometry(
    val location: Location
)

data class Location(
    val lat: Double,
    val lng: Double
)