package com.example.weatherforecast.util

import com.example.weatherforecast.repository.model.api.ForecastApi
import com.example.weatherforecast.repository.model.api.WeatherApi
import com.example.weatherforecast.repository.model.local.Forecast
import com.example.weatherforecast.repository.model.local.Weather
import com.example.weatherforecast.repository.model.local.WeatherData

object WeatherBuilder {

    fun buildWeather(weatherApi: WeatherApi) : Weather {
        val weather = weatherApi.weather?.get(0)
        val main = weatherApi.main
        val wind = weatherApi.wind

        return Weather(weatherApi.name, WeatherData(weatherApi.dt, weather?.main,
            weather?.description, main?.temp, main?.pressure, main?.humidity, main?.tempMin, main?.tempMax,
            wind?.speed, wind?.deg, weatherApi.clouds?.all))
    }

    fun buildForecast(forecastApi: ForecastApi) : Forecast {
        val city = forecastApi.city.name
        val weatherDataList = ArrayList<WeatherData>()

        if (forecastApi.list != null) {
            for (data in forecastApi.list) {
                val weather = data.weather?.get(0)
                val main = data.main
                val wind = data.wind
                weatherDataList.add(WeatherData(data.dt, weather?.main, weather?.description, main?.temp,
                    main?.pressure, main?.humidity, main?.tempMin, main?.tempMax, wind?.speed, wind?.deg,
                    data.clouds?.all))
            }
        }

        return Forecast(city, weatherDataList)
    }
}