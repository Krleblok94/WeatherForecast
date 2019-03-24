package com.example.weatherforecast.repository.model.local

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class Forecast(
    @PrimaryKey
    val city: String,
    val weatherDataList: List<WeatherData>
) {
    fun print() {
        val tag = "DebugTag"
        Log.d(tag, "==================FORECAST====================")
        Log.d(tag, "City: $city")
        for (data in weatherDataList) {
            data.print()
        }
        Log.d(tag, "/////////////////////////////////////////////")
    }
}