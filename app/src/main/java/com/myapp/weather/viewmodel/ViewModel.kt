package com.myapp.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.model.WeatherModelGPS
import com.myapp.weather.model.WeatherModelName
import com.myapp.weather.repository.cityName.CityNameRepository
import com.myapp.weather.repository.coords.CoordsRepository
import com.myapp.weather.repository.weatherForecast.WeatherForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val coordsRepository: CoordsRepository,
    private val cityNameRepository: CityNameRepository
) : ViewModel() {

    private val _dataGps = MutableLiveData(WeatherModelGPS())
    val dataGPS: LiveData<WeatherModelGPS>
        get() = _dataGps
    private val _dataName = MutableLiveData(WeatherModelName())
    val dataName: LiveData<WeatherModelName>
        get() = _dataName

    fun loadWeatherByName(name: String?) = viewModelScope.launch {
        try {
            _dataName.value = WeatherModelName(loading = true)
            val dailyForecast = name?.let { weatherForecastRepository.getDailyForecastByName(it) }
            val forecast5Days = name?.let { weatherForecastRepository.getForecast5DaysByName(it) }
            _dataName.value = WeatherModelName(
                weatherForecastDaily = dailyForecast,
                weatherForecast5Days = forecast5Days
            )
        } catch (e: Exception) {
            _dataName.value = WeatherModelName(error = true)
            e.printStackTrace()
        }
    }

    fun loadWeatherByGps(lat: Double, lon: Double) = viewModelScope.launch {
        try {
            _dataGps.value = WeatherModelGPS(loading = true)
            val dailyForecast = weatherForecastRepository.getDailyForecastByGPS(lat, lon)
            val forecast5Days = weatherForecastRepository.getForecast5DaysByGPS(lat, lon)
            _dataGps.value = WeatherModelGPS(
                weatherForecastDaily = dailyForecast,
                weatherForecast5Days = forecast5Days
            )
        } catch (e: Exception) {
            _dataGps.value = WeatherModelGPS(error = true)
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

    fun getCityName(): String {
        return cityNameRepository.getName()
    }

    fun saveCityName(name: String) {
        cityNameRepository.saveName(name)
    }
}



