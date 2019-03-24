package com.example.weatherforecast.util

object Constants {

    const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    const val CURRENT_ENDPOINT = "weather"
    const val FORECAST_ENDPOINT = "forecast"

    const val APP_ID = "b434fc4e662ba4a49dfaa4f41fad7c82"
    const val TIMEOUT_IN_SEC = 15L

    const val API_RATE_LIMITER_KEY_WEATHER = "apiRateLimiterKeyWeather"
    const val API_RATE_LIMITER_KEY_FORECAST = "apiRateLimiterKeyForecast"

    // Status codes for network bound resource
    object Status {
        const val SUCCESS = 1
        const val LOADING = 0
        const val ERROR = -1
    }

    object DateTime {
        const val DATE = 0
        const val TIME = 1
        const val DATE_TIME = 2
    }
}