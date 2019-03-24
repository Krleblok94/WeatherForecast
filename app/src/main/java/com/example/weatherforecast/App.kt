package com.example.weatherforecast

import android.app.Application
import com.example.weatherforecast.di.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}