package com.example.weatherapp1.domain.repository

import com.example.weatherapp1.data.api.WeatherService
import com.example.weatherapp1.domain.base.BaseRepository
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherService
): BaseRepository() {
    fun fetchWeatherForecast(location: String) = doRequest { api.getWeatherForecast(location, 1) }
}