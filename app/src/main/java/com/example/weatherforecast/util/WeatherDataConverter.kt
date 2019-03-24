package com.example.weatherforecast.util

import androidx.room.TypeConverter
import com.example.weatherforecast.repository.model.local.WeatherData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object WeatherDataConverter {

    @TypeConverter
    @JvmStatic
    fun fromWeatherData(weatherData: WeatherData?) : String {
        return Gson().toJson(weatherData)
    }

    @TypeConverter
    @JvmStatic
    fun toWeatherData(value: String) : WeatherData {
        val type = object : TypeToken<WeatherData>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromWeatherDataList(weatherDataList: List<WeatherData>?) : String {
        return Gson().toJson(weatherDataList)
    }

    @TypeConverter
    @JvmStatic
    fun toWeatherDataList(value: String) : List<WeatherData> {
        val type = object : TypeToken<List<WeatherData>>() {}.type
        return Gson().fromJson(value, type)
    }
}