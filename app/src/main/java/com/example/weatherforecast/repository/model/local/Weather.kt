package com.example.weatherforecast.repository.model.local

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey
    val city: String,
    val weatherData: WeatherData
) {
    fun print() {
        val tag = "DebugTag"
        Log.d(tag, "==================WEATHER====================")
        Log.d(tag, "City: $city")
        weatherData.print()
        Log.d(tag, "/////////////////////////////////////////////")
    }
}