package com.example.weatherforecast.ui

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R


class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvDate: TextView = view.findViewById(R.id.tvDate)
    val tvTime: TextView = view.findViewById(R.id.tvTime)
    val tvTemp: TextView = view.findViewById(R.id.tvTemp)
    val tvTempMax: TextView = view.findViewById(R.id.tvTempMax)
    val tvTempMin: TextView = view.findViewById(R.id.tvTempMin)
    val tvWeatherType: TextView = view.findViewById(R.id.tvWeatherType)
    val tvWeatherDescription: TextView = view.findViewById(R.id.tvWeatherDescription)
    val tvCloudPercentage: TextView = view.findViewById(R.id.tvCloudPercentage)
    val tvPressure: TextView = view.findViewById(R.id.tvPressure)
    val tvHumidity: TextView = view.findViewById(R.id.tvHumidity)
    val tvWindSpeed: TextView = view.findViewById(R.id.tvWindSpeed)
    val tvWindDegree: TextView = view.findViewById(R.id.tvWindDegree)
    val clDetails: ConstraintLayout = view.findViewById(R.id.detailsContainer)
}