package com.example.weatherapp1.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp1.data.dao.WeatherDao
import com.example.weatherapp1.data.db.MyDatabase
import com.example.weatherapp1.data.model.room.RoomWeather
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DbModule {
    lateinit var weatherDao: WeatherDao
fun init(context: Context) {
    val db = Room.databaseBuilder(
        context = context,
        klass = MyDatabase::class.java,
        name = "my_database"
    ).allowMainThreadQueries().build()

    weatherDao = db.weatherDao()
}
}