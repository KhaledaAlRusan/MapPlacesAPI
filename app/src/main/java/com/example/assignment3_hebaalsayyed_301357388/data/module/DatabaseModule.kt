package com.example.assignment3_hebaalsayyed_301357388.data.module

import android.content.Context
import androidx.room.Room
import com.example.assignment3_hebaalsayyed_301357388.data.PlacesDao
import com.example.assignment3_hebaalsayyed_301357388.core.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "MyDatabase.db"
        ).build()
    }

    @Provides
    fun providePlacesDao(database: AppDatabase): PlacesDao {
        return database.placesDao()
    }
}
