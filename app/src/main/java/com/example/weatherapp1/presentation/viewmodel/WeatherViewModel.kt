package com.example.weatherapp1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.weatherapp1.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    fun getWeatherForecast(city: String) = repository.fetchWeatherForecast(city)
}