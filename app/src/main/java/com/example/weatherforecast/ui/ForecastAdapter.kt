package com.example.weatherforecast.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.repository.model.local.WeatherData
import com.example.weatherforecast.util.Constants
import com.example.weatherforecast.util.ViewUtils

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {

    private val weatherDataList = mutableListOf<WeatherData>()
    private val showDateIndicesList = mutableMapOf<Int, String>()
    private var expandedPosition = -1
    private var previousExpandedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent,
            false))
    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val weatherData = weatherDataList[position]
        if (showDateIndicesList.containsKey(position)) {
            holder.tvDate.visibility = View.VISIBLE
            holder.tvDate.text = showDateIndicesList[position]
        } else {
            holder.tvDate.visibility = View.GONE
        }

        if (weatherData.temp != null) {
            val currentTemperature = "${Math.round(weatherData.temp)}\u2103"
            holder.tvTemp.text = currentTemperature
        }

        if (weatherData.tempMax != null && weatherData.tempMin != null) {
            val maxTemperature = "Max: ${Math.round(weatherData.tempMax)}\u2103"
            val minTemperature = "Min: ${Math.round(weatherData.tempMin)}\u2103"
            holder.tvTempMax.text = maxTemperature
            holder.tvTempMin.text = minTemperature
        }

        if (weatherData.timeOfMeasurement != null) {
            holder.tvTime.text = ViewUtils.getFromTimestamp(weatherData.timeOfMeasurement.toLong(),
                Constants.DateTime.TIME)
        }

        val isExpanded = expandedPosition == position
        holder.clDetails.visibility = if (isExpanded) {
            previousExpandedPosition = expandedPosition
            View.VISIBLE
        } else {
            View.GONE
        }
        holder.itemView.isActivated = isExpanded
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(previousExpandedPosition)
            notifyItemChanged(position)
        }

        val weatherType = "Weather type: ${weatherData.weatherType}"
        val weatherDescription = "Weather description: ${weatherData.weatherDescription}"
        val cloudPercentage = "Clouds: ${weatherData.clouds} %"
        val pressure = "Pressure: ${weatherData.pressure} hPa"
        val humidity = "Humidity: ${weatherData.humidity} %"
        val windSpeed = "Wind speed: ${weatherData.windSpeed} m/s"
        val windDegree = "Wind degree: ${weatherData.windDegree}\u00B0"
        holder.tvWeatherType.text = weatherType
        holder.tvWeatherDescription.text = weatherDescription
        holder.tvCloudPercentage.text = cloudPercentage
        holder.tvPressure.text = pressure
        holder.tvHumidity.text = humidity
        holder.tvWindSpeed.text = windSpeed
        holder.tvWindDegree.text = windDegree
    }

    fun addList(weatherDataList: List<WeatherData>) {
        this.weatherDataList.clear()
        this.weatherDataList.addAll(weatherDataList)

        var lastDate = ""
        for (i in 0 until weatherDataList.size) {
            val weatherData = weatherDataList[i]
            if (weatherData.timeOfMeasurement != null) {
                val dateString = ViewUtils.getFromTimestamp(weatherData.timeOfMeasurement.toLong(),
                    Constants.DateTime.DATE)
                if (lastDate != dateString) {
                    showDateIndicesList[i] = dateString
                    lastDate = dateString
                }
            }
        }

        notifyDataSetChanged()
    }
}