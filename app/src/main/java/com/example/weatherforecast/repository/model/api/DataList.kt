package com.example.weatherforecast.repository.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DataList(
    @SerializedName("dt")
    @Expose
    val dt: Int?,
    @SerializedName("main")
    @Expose
    val main: Main?,
    @SerializedName("weather")
    @Expose
    val weather: List<Weather>?,
    @SerializedName("clouds")
    @Expose
    val clouds: Clouds?,
    @SerializedName("wind")
    @Expose
    val wind: Wind?,
    @SerializedName("sys")
    @Expose
    val sys: Sys?,
    @SerializedName("dt_txt")
    @Expose
    val dtTxt: String?
)