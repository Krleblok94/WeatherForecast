package com.example.weatherforecast.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherforecast.repository.model.local.Forecast
import com.example.weatherforecast.repository.model.local.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE lower(city) = :city")
    fun getWeatherForCity(city: String) : LiveData<Weather>

    @Query("SELECT * FROM forecast WHERE lower(city) = :city")
    fun getForecastForCity(city: String) : LiveData<Forecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: Weather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(forecast: Forecast)

    @Query("DELETE FROM weather WHERE lower(city) = :city")
    fun deleteWeatherForCity(city: String)

    @Query("DELETE FROM forecast WHERE lower(city) = :city")
    fun deleteForecastForCity(city: String)
}