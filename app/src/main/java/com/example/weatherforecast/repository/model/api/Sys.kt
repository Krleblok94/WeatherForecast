package com.example.weatherforecast.repository.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("type")
    @Expose
    val type: Int?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("message")
    @Expose
    val message: Double?,
    @SerializedName("country")
    @Expose
    val country: String?,
    @SerializedName("sunrise")
    @Expose
    val sunrise: Int?,
    @SerializedName("sunset")
    @Expose
    val sunset: Int?
)