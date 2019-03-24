package com.example.weatherforecast.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.weatherforecast.R
import com.example.weatherforecast.util.ViewUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val weatherViewModel by viewModel<WeatherViewModel>()
    private val pagerAdapter by inject<PagerAdapter> { parametersOf(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                doSearch()
            }
            false
        }

        ivSearch.setOnClickListener {
            doSearch()
        }

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun doSearch() {
        val query = etSearch.text.toString()
        if (!TextUtils.isEmpty(query) && !TextUtils.equals(query, "null")) {
            etSearch.clearFocus()
            ViewUtils.hideSoftKeyboard(this.currentFocus)

            weatherViewModel.setCity(query.toLowerCase())
        }
    }

    class PagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> CurrentFragment.newInstance()
                1 -> ForecastFragment.newInstance()
                else -> CurrentFragment.newInstance()
            }
        }

        override fun getCount(): Int = 2

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Current"
                1 -> "Forecast"
                else -> ""
            }
        }

    }
}
