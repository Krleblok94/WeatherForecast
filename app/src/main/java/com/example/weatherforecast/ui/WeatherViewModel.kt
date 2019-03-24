package com.example.weatherforecast.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.repository.WeatherRepository

class WeatherViewModel(weatherRepository: WeatherRepository) : ViewModel() {
    private val city = MutableLiveData<String>()
    val weather = Transformations.switchMap(city) {
        weatherRepository.loadWeather(it)
    }

    val forecast = Transformations.switchMap(city) {
        weatherRepository.loadForecast(it)
    }

    fun setCity(newCity: String) {
        if (!city.value.equals(newCity)) {
            city.value = newCity
        }
    }
}