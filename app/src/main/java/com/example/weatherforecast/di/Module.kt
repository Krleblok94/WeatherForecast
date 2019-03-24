package com.example.weatherforecast.di

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.room.Room
import com.example.weatherforecast.BuildConfig
import com.example.weatherforecast.repository.WeatherRepository
import com.example.weatherforecast.repository.database.AppDatabase
import com.example.weatherforecast.repository.network.ApiService
import com.example.weatherforecast.ui.ForecastAdapter
import com.example.weatherforecast.ui.MainActivity
import com.example.weatherforecast.ui.WeatherViewModel
import com.example.weatherforecast.util.AppExecutors
import com.example.weatherforecast.util.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { provideApiService() }
    single { provideAppDatabase(androidApplication()) }
    single { AppExecutors() }
    single { WeatherRepository(get(), get(), get()) }

    viewModel { WeatherViewModel(get()) }

    factory { (fragmentManager: FragmentManager) -> MainActivity.PagerAdapter(fragmentManager) }
    factory { ForecastAdapter() }
}

private fun provideApiService() : ApiService {
    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.connectTimeout(Constants.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
    okHttpClient.readTimeout(Constants.TIMEOUT_IN_SEC, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(httpLoggingInterceptor)
    }

    val gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient.build())
        .build()

    return retrofit.create(ApiService::class.java)
}

private fun provideAppDatabase(application: Application) : AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "app_database.db")
        .fallbackToDestructiveMigration().build()
}