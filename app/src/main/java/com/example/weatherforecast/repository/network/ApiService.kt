package com.example.weatherforecast.repository.network

import com.example.weatherforecast.repository.model.api.WeatherApi
import com.example.weatherforecast.repository.model.api.ForecastApi
import com.example.weatherforecast.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.CURRENT_ENDPOINT)
    fun getCurrentWeather(@Query("q") q: String, @Query("units") units: String = "metric",
                          @Query("appid") apiKey: String = Constants.APP_ID) : Call<WeatherApi>

    @GET(Constants.FORECAST_ENDPOINT)
    fun getFiveDaysForecast(@Query("q") q: String, @Query("units") units: String = "metric",
                            @Query("appid") apiKey: String = Constants.APP_ID) : Call<ForecastApi>
}