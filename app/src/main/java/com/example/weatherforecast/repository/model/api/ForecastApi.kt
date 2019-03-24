package com.example.weatherforecast.repository.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ForecastApi(
    @SerializedName("city")
    @Expose
    val city: City,
    @SerializedName("coord")
    @Expose
    val coord: Coord?,
    @SerializedName("country")
    @Expose
    val country: String?,
    @SerializedName("cod")
    @Expose
    val cod: String?,
    @SerializedName("message")
    @Expose
    val message: Double?,
    @SerializedName("cnt")
    @Expose
    val cnt: Int?,
    @SerializedName("list")
    @Expose
    val list: List<DataList>?
)