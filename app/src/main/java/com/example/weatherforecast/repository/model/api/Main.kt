package com.example.weatherforecast.repository.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main(
    @SerializedName("temp")
    @Expose
    val temp: Double?,
    @SerializedName("pressure")
    @Expose
    val pressure: Double?,
    @SerializedName("humidity")
    @Expose
    val humidity: Int?,
    @SerializedName("temp_min")
    @Expose
    val tempMin: Double?,
    @SerializedName("temp_max")
    @Expose
    val tempMax: Double?
)