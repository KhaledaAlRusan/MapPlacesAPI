package com.example.assignment3_hebaalsayyed_301357388

import android.app.Application
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application(){
    override fun onCreate() {
        super.onCreate()
        Places.initialize(this, "AIzaSyD8ys_MCMj3eQ7i9DdvEOKvIsrOMjDAe3U")
    }
}