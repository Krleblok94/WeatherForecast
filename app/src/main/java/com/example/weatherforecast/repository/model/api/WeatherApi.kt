package com.example.weatherforecast.repository.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherApi(
    @SerializedName("coord")
    @Expose
    val coord: Coord?,
    @SerializedName("weather")
    @Expose
    val weather: List<Weather>?,
    @SerializedName("base")
    @Expose
    val base: String?,
    @SerializedName("main")
    @Expose
    val main: Main?,
    @SerializedName("wind")
    @Expose
    val wind: Wind?,
    @SerializedName("clouds")
    @Expose
    val clouds: Clouds?,
    @SerializedName("rain")
    @Expose
    val rain: Rain?,
    @SerializedName("dt")
    @Expose
    val dt: Int?,
    @SerializedName("sys")
    @Expose
    val sys: Sys?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("cod")
    @Expose
    val cod: Int?
)