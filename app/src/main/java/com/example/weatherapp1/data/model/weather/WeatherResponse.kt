package com.example.weatherapp1.data.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
data class Location(
    val name: String
)
data class Forecast(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>
)
data class Current(
    @SerializedName("temp_c")
    val temp: Double
)
data class ForecastDay(
    val hour: List<Hour>
)