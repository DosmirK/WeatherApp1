package com.example.weatherapp1.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp1.data.model.room.RoomWeather
import com.example.weatherapp1.data.model.room.RoomWeatherHour

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getWeather()

    @Query("SELECT * FROM hours")
    fun getHourly()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeather(roomWeather: RoomWeather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHours(hourly: ArrayList<RoomWeatherHour>)

    @Query("DELETE FROM weather")
    fun clearWeather()

    @Query("DELETE FROM hours")
    fun clearHours()

}