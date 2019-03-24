package com.example.weatherforecast.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherforecast.util.WeatherDataConverter
import com.example.weatherforecast.repository.model.local.Forecast
import com.example.weatherforecast.repository.model.local.Weather

@Database(entities = [Weather::class, Forecast::class], version = 1)
@TypeConverters(WeatherDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao
}