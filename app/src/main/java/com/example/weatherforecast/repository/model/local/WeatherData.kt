package com.example.weatherforecast.repository.model.local

import android.util.Log

data class WeatherData(
    val timeOfMeasurement: Int?,
    val weatherType: String?,
    val weatherDescription: String?,
    val temp: Double?,
    val pressure: Double?,
    val humidity: Int?,
    val tempMin: Double?,
    val tempMax: Double?,
    val windSpeed: Double?,
    val windDegree: Double?,
    val clouds: Int?
) {
    fun print() {
        val tag = "DebugTag"
        Log.d(tag, "Time of measurement: $timeOfMeasurement, weather type: $weatherType, " +
                "weather desc: $weatherDescription, temp: $temp, pressure: $pressure, humidity: $humidity, " +
                "temp min: $tempMin, temp max: $tempMax, wind speed: $windSpeed, wind degree: $windDegree, " +
                "clouds: $clouds")
    }
}