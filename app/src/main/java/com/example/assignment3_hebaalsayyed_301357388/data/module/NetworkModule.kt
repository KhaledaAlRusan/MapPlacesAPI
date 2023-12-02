package com.example.assignment3_hebaalsayyed_301357388.data.module

import com.example.assignment3_hebaalsayyed_301357388.data.PlacesAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val baseUrl = "https://maps.googleapis.com/maps/"

    //Provide retrofit instance
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Provide API service
    @Provides
    @Singleton
    fun provideGoogleAPIService(retrofit: Retrofit): PlacesAPIService {
        return retrofit.create(PlacesAPIService::class.java)
    }

}