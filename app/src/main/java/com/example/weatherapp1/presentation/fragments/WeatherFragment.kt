package com.example.weatherapp1.presentation.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.example.weatherapp1.data.model.weather.ForecastDay
import com.example.weatherapp1.data.model.weather.Hour
import com.example.weatherapp1.databinding.FragmentWeatherBinding
import com.example.weatherapp1.presentation.adapter.WeatherAdapter
import com.example.weatherapp1.presentation.fragments.base.BaseFragment
import com.example.weatherapp1.presentation.utils.isPermissionGranted
import com.example.weatherapp1.presentation.viewmodel.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class WeatherFragment : BaseFragment<
        FragmentWeatherBinding, WeatherViewModel>
    (FragmentWeatherBinding::inflate) {

    override val viewModel: WeatherViewModel by viewModels()
    private val adapter: WeatherAdapter by lazy {
        WeatherAdapter()
    }
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var fLocationClient: FusedLocationProviderClient
    private var city: String = "Bishkek"

    override fun checkPermission() {
        super.checkPermission()
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun permissionListener(){
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    override fun getLocation() {
        super.getLocation()
        val ct = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, ct.token)
            .addOnCompleteListener() {
                city = "${it.result.latitude},${it.result.longitude}"
            }
    }

    override fun observe() {
        super.observe()
        viewModel.getWeatherForecast(city).resHandler({}, { weather ->
            Log.e("ololo", "${weather}")
            binding.apply {
                tvCityFull.text = weather.location.name
                val initial = convertToInitials(weather.location.name)
                tvCityInitial.text = initial
                val temp = weather.current.temp.toString() + "°"
                tvTemp.text = temp

                val forecastDays: List<ForecastDay> = weather.forecast.forecastDay

                val allHours: MutableList<Hour> = mutableListOf()

                for (forecastDay in forecastDays) {
                    val hoursOfDay: List<Hour> = forecastDay.hour
                    allHours.addAll(hoursOfDay)
                }
               adapter.submitList(allHours)
            }
        })
    }

   private fun convertToInitials(word: String): String{
        val firstLetter = word.substring(0, 1).toUpperCase()
        val thirdLetter = word.substring(2, 3).toUpperCase()
        val sixthLetter = word.substring(5, 6).toUpperCase()

        return "$firstLetter$thirdLetter$sixthLetter"
    }

    override fun initialize() = with(binding) {
        fLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        super.initialize()
        rvWeather.adapter = adapter
    }
}