package com.myapp.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.model.WeatherModel
import com.myapp.weather.repository.coords.CoordsRepository
import com.myapp.weather.repository.weatherForecast.WeatherForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val coordsRepository: CoordsRepository
) : ViewModel() {

    private val _data = MutableLiveData(WeatherModel())
    val data: LiveData<WeatherModel>
        get() = _data

    init {
        val lat = getLat()
        val lon = getLon()
        if (lat != 0.0 && lon != 0.0) {
            loadWeatherByGps(lat, lon)
        }
    }

    fun loadWeatherByName(name: String?) = viewModelScope.launch {
        try {
            _data.value = WeatherModel(loading = true)
            val dailyForecast = name?.let { weatherForecastRepository.getDailyForecastByName(it) }
            val forecast5Days = name?.let { weatherForecastRepository.getForecast5DaysByName(it) }
            _data.value = WeatherModel(
                weatherForecastDaily = dailyForecast,
                weatherForecast5Days = forecast5Days
            )
        } catch (e: Exception) {
            _data.value = WeatherModel(error = true)

        }
    }

    fun loadWeatherByGps(lat: Double, lon: Double) = viewModelScope.launch {
        try {
            _data.value = WeatherModel(loading = true)
            val dailyForecast = weatherForecastRepository.getDailyForecastByGPS(lat, lon)
            val forecast5Days = weatherForecastRepository.getForecast5DaysByGPS(lat, lon)
            _data.value = WeatherModel(
                weatherForecastDaily = dailyForecast,
                weatherForecast5Days = forecast5Days
            )
        } catch (e: Exception) {
            _data.value = WeatherModel(error = true)
            e.printStackTrace()
        }
    }

    fun getLon(): Double {
        return if (coordsRepository.getLon() == "") {
            0.0
        } else return coordsRepository.getLon().toDouble()
    }

    fun getLat(): Double {
        return if (coordsRepository.getLat() == "") {
            0.0
        } else return coordsRepository.getLat().toDouble()
    }

    fun saveLon(lon: Double) {
        coordsRepository.saveLon(lon.toString())
    }

    fun saveLat(lat: Double) {
        coordsRepository.saveLat(lat.toString())
    }
}



