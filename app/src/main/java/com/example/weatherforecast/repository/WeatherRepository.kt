package com.example.weatherforecast.repository

import androidx.lifecycle.LiveData
import com.example.weatherforecast.repository.database.AppDatabase
import com.example.weatherforecast.repository.model.api.ForecastApi
import com.example.weatherforecast.repository.model.api.WeatherApi
import com.example.weatherforecast.repository.model.local.Forecast
import com.example.weatherforecast.repository.model.local.Weather
import com.example.weatherforecast.repository.network.ApiService
import com.example.weatherforecast.util.*
import retrofit2.Call
import java.util.concurrent.TimeUnit

class WeatherRepository(private val appExecutors: AppExecutors,
                        private val appDatabase: AppDatabase,
                        private val apiService: ApiService) {

    private val rateLimiter = RateLimiter<String>(1, TimeUnit.MINUTES)

    fun loadWeather(city: String) : LiveData<Resource<Weather>> =
        object : NetworkBoundResource<Weather, WeatherApi>(appExecutors) {
            // Note: This method is always called on a background thread
            override fun saveCallResult(item: WeatherApi) {
                appDatabase.runInTransaction {
                    appDatabase.weatherDao().deleteWeatherForCity(city)
                    appDatabase.weatherDao().insertWeather(WeatherBuilder.buildWeather(item))
                }
            }

            override fun shouldFetch(data: Weather?): Boolean = data == null
                    || rateLimiter.shouldFetch(Constants.API_RATE_LIMITER_KEY_WEATHER)

            override fun loadFromDb(): LiveData<Weather> = appDatabase.weatherDao().getWeatherForCity(city)

            override fun createCall(): Call<WeatherApi>? = apiService.getCurrentWeather(city)

            override fun onFetchFailed() {
                rateLimiter.reset(Constants.API_RATE_LIMITER_KEY_WEATHER)
            }

        }.asLiveData()

    fun loadForecast(city: String) : LiveData<Resource<Forecast>> =
        object : NetworkBoundResource<Forecast, ForecastApi>(appExecutors) {

        override fun saveCallResult(item: ForecastApi) {
            appDatabase.runInTransaction {
                appDatabase.weatherDao().deleteForecastForCity(city)
                appDatabase.weatherDao().insertForecast(WeatherBuilder.buildForecast(item))
            }
        }

        override fun shouldFetch(data: Forecast?): Boolean = data == null
                        || rateLimiter.shouldFetch(Constants.API_RATE_LIMITER_KEY_FORECAST)

        override fun loadFromDb(): LiveData<Forecast> = appDatabase.weatherDao().getForecastForCity(city)

        override fun createCall(): Call<ForecastApi>? = apiService.getFiveDaysForecast(city)

        override fun onFetchFailed() {
            rateLimiter.reset(Constants.API_RATE_LIMITER_KEY_FORECAST)
        }

    }.asLiveData()

}