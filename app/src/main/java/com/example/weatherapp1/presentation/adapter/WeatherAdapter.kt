package com.example.weatherapp1.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp1.data.model.weather.Hour
import com.example.weatherapp1.databinding.ItemWeatherBinding

class WeatherAdapter :
    ListAdapter<Hour, WeatherAdapter.WeatherStepViewHolder>(DIFF_UTIL_CALLBACK) {
    private companion object {
        val DIFF_UTIL_CALLBACK: DiffUtil.ItemCallback<Hour> =
            object : DiffUtil.ItemCallback<Hour>() {
                override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
                    return oldItem == newItem
                }
                override fun areContentsTheSame(
                    oldItem: Hour,
                    newItem: Hour
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherStepViewHolder {
        return WeatherStepViewHolder(
            ItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
    override fun onBindViewHolder(holder: WeatherStepViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
    inner class WeatherStepViewHolder(private val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(weather: Hour) {
            Log.e("ololo", "${weather}")
            binding.apply {
                tvTime.text = weather.time.substring(11, 15)
                val rain = weather.chance_of_rain.toString() + "%"
                tvHumidity.text = rain
                ivImage.load("http:${weather.condition.icon}")
            }
        }
    }
}