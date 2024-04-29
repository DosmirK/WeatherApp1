package com.example.weatherapp1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp1.data.dao.WeatherDao
import com.example.weatherapp1.data.model.room.RoomWeather

@Database(
    entities = [
        RoomWeather::class
    ],
    version = 1
)
abstract class MyDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}