package com.example.weatherapp1.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp1.data.model.room.RoomWeather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getWeather(): List<RoomWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeather(roomWeather: RoomWeather)

    @Query("DELETE FROM weather")
    fun clearWeather()
}