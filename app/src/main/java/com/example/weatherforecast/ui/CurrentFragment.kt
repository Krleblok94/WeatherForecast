package com.example.weatherforecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.weatherforecast.R
import com.example.weatherforecast.util.Constants
import com.example.weatherforecast.util.ViewUtils
import kotlinx.android.synthetic.main.fragment_current.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CurrentFragment : Fragment() {

    private val weatherViewModel by sharedViewModel<WeatherViewModel>()

    companion object {
        fun newInstance() = CurrentFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
        return inflater.inflate(R.layout.fragment_current, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherViewModel.weather.observe(this, Observer {

            tvNoData.visibility = View.GONE

            progressBar.visibility = if (it.networkStatus.status == Constants.Status.LOADING)
                View.VISIBLE else View.GONE

            errorContainer.visibility = if (it.networkStatus.status == Constants.Status.ERROR)
                View.VISIBLE else View.GONE

            dataContainer.visibility = if (it.networkStatus.status == Constants.Status.SUCCESS)
                View.VISIBLE else View.GONE

            if (it.networkStatus.status == Constants.Status.SUCCESS) {
                if (it.data?.weatherData?.timeOfMeasurement != null) {
                    val dateString = "Last measured: " + ViewUtils.getFromTimestamp(
                        it.data.weatherData.timeOfMeasurement.toLong(), Constants.DateTime.DATE_TIME)
                    tvMeasuredAt.text = dateString
                }

                tvCity.text = it.data?.city
                if (it.data?.weatherData?.temp != null) {
                    val currentTemperature = "${Math.round(it.data.weatherData.temp)}\u2103"
                    tvCurrentTemperature.text = currentTemperature
                }

                if (it.data?.weatherData?.tempMax != null && it.data.weatherData.tempMin != null) {
                    val maxTemperature = "Max: ${Math.round(it.data.weatherData.tempMax)}\u2103"
                    val minTemperature = "Min: ${Math.round(it.data.weatherData.tempMin)}\u2103"
                    tvTempMax.text = maxTemperature
                    tvTempMin.text = minTemperature
                }

                val weatherType = "Weather type: ${it.data?.weatherData?.weatherType}"
                val weatherDescription = "Weather description: ${it.data?.weatherData?.weatherDescription}"
                val cloudPercentage = "Clouds: ${it.data?.weatherData?.clouds} %"
                val pressure = "Pressure: ${it.data?.weatherData?.pressure} hPa"
                val humidity = "Humidity: ${it.data?.weatherData?.humidity} %"
                val windSpeed = "Wind speed: ${it.data?.weatherData?.windSpeed} m/s"
                val windDegree = "Wind degree: ${it.data?.weatherData?.windDegree}\u00B0"
                tvWeatherType.text = weatherType
                tvWeatherDescription.text = weatherDescription
                tvCloudPercentage.text = cloudPercentage
                tvPressure.text = pressure
                tvHumidity.text = humidity
                tvWindSpeed.text = windSpeed
                tvWindDegree.text = windDegree
            }
        })
    }
}