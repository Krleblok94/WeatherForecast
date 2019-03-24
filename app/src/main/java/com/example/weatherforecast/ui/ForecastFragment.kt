package com.example.weatherforecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.util.Constants
import kotlinx.android.synthetic.main.fragment_forecast.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ForecastFragment : Fragment() {

    private val weatherViewModel by sharedViewModel<WeatherViewModel>()
    private val forecastAdapter by inject<ForecastAdapter>()
    private lateinit var dataList: RecyclerView

    companion object {
        fun newInstance() = ForecastFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
        val root = inflater.inflate(R.layout.fragment_forecast, container, false)
        dataList = root.findViewById(R.id.dataList)
        dataList.adapter = forecastAdapter
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherViewModel.forecast.observe(this, Observer {

            tvNoData.visibility = View.GONE

            progressBar.visibility = if (it.networkStatus.status == Constants.Status.LOADING)
                View.VISIBLE else View.GONE

            errorContainer.visibility = if (it.networkStatus.status == Constants.Status.ERROR)
                View.VISIBLE else View.GONE

            dataList.visibility = if (it.networkStatus.status == Constants.Status.SUCCESS)
                View.VISIBLE else View.GONE

            if (it.networkStatus.status == Constants.Status.SUCCESS && it.data?.weatherDataList != null) {
                forecastAdapter.addList(it.data.weatherDataList)
            }
        })
    }

}