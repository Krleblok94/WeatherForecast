package com.example.weatherforecast.util

import android.content.Context
import android.text.format.DateFormat
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*

object ViewUtils {
    fun hideSoftKeyboard(view: View?) {
        view?.let { v ->
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as?
                    InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    // Returns either the date, time or datetime formatted from timestamp
    fun getFromTimestamp(time: Long, type: Int) : String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time * 1000

        val format = when (type) {
            Constants.DateTime.DATE -> "EEE, MMM d, yyyy"
            Constants.DateTime.TIME -> "HH:mm"
            Constants.DateTime.DATE_TIME -> "EEE, MMM d, yyyy 'at' HH:mm"
            else -> "EEE, MMM d, yyyy 'at' HH:mm"
        }
        return DateFormat.format(format, calendar).toString()
    }
}