package com.example.weatherapp1.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class RoomWeather(
    val cityName: String,
    val cityInitial: String,
    val temperature: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hour: String,
    val rainPercentage: String,
    val imageUrl: String,
    val dateUnix: Long
)