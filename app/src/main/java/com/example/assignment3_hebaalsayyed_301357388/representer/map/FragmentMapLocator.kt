package com.example.assignment3_hebaalsayyed_301357388.representer.map

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.assignment3_hebaalsayyed_301357388.domain.model.PlaceResult
import com.example.assignment3_hebaalsayyed_301357388.R
import com.example.assignment3_hebaalsayyed_301357388.databinding.FragmentMapLocatorBinding
import com.example.assignment3_hebaalsayyed_301357388.domain.model.PlaceEntity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMapLocator: Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapLocatorBinding
    private val viewModel: PlacesViewModel by viewModels()
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapLocatorBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupUI()
    }

    private fun setupUI() {
        binding.btnLocate.setOnClickListener {
            val selectedCity = binding.spnrCities.selectedItem.toString()
            val selectedCategory = binding.spnrCategories.selectedItem.toString()
            viewModel.fetchPlaces(selectedCity, selectedCategory)
        }

        binding.btnCheckDB.setOnClickListener {
            googleMap.clear()
            val selectedCity = binding.spnrCities.selectedItem.toString()
            val selectedCategory = binding.spnrCategories.selectedItem.toString()
            viewModel.fetchPlacesByCategory(selectedCategory,selectedCity)
        }

        viewModel.placesFromDB.observe(viewLifecycleOwner) { savedPlaces ->
            if (savedPlaces.isNotEmpty()) {
                updateMapWithSavedPlaces(savedPlaces)
            } else {
                // Handle empty data case, e.g., show a message
            }
        }


        viewModel.placesData.observe(viewLifecycleOwner) { places ->
            updateMapWithPlaces(places.results)
        }
    }
    private fun updateMapWithSavedPlaces(savedPlaces: List<PlaceEntity>) {
        googleMap.clear()
        val markerPlaceMap = HashMap<Marker, PlaceEntity>()

        savedPlaces.forEach { place ->
            val location = LatLng(place.latitude, place.longitude)
            val marker = googleMap.addMarker(MarkerOptions().position(location).title(place.name))
            marker?.let { markerPlaceMap[it] = place }
        }

        googleMap.setOnMarkerClickListener { marker ->
            markerPlaceMap[marker]?.let { place ->
                showSavedPlaceDetails(place)
            }
            true
        }

        // Optionally, move the camera to the first location
        if (savedPlaces.isNotEmpty()) {
            moveCameraToLocation(LatLng(savedPlaces.first().latitude, savedPlaces.first().longitude))
        }
    }

    private fun showSavedPlaceDetails(place: PlaceEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle(place.name)
            .setMessage("Saved Place: ${place.name}\nAddress: ${place.formattedAddress}")
            .setPositiveButton("OK", null)
            .show()
    }




    private fun updateMapWithPlaces(places: List<PlaceResult>) {
        googleMap.clear()
        val markerPlaceMap = HashMap<Marker, PlaceResult>()

        places.forEach { place ->
            val location = LatLng(place.geometry.location.lat, place.geometry.location.lng)

            val marker = googleMap.addMarker(MarkerOptions().position(location).title(place.name))
            marker?.let { markerPlaceMap[it] = place }
        }

        googleMap.setOnMarkerClickListener { marker ->
            markerPlaceMap[marker]?.let { place ->
                showPlaceDetails(place)
            }
            true
        }

        if (places.isNotEmpty()) {
            val location = LatLng(places.first().geometry.location.lat,places.first().geometry.location.lng)
            moveCameraToLocation(location)
        }
    }

    private fun showPlaceDetails(place: PlaceResult) {
        AlertDialog.Builder(requireContext())
            .setTitle(place.name)
            .setMessage("Selected Place: ${place.name}\nAddress: ${place.formatted_address}")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun moveCameraToLocation(location: LatLng) {
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 12f)
        googleMap.animateCamera(cameraUpdate)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }
}