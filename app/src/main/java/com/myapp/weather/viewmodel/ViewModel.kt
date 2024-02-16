package com.myapp.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.model.WeatherModel
import com.myapp.weather.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _data = MutableLiveData(WeatherModel())
    val data: LiveData<WeatherModel>
        get() = _data

    init {
        loadWeather()
    }

    fun loadWeather() = viewModelScope.launch {
        try {
            _data.value = WeatherModel(loading = true)
            val input = repository.getAll()
            _data.value = WeatherModel(weatherForecast = input)
        } catch (e: Exception) {
            _data.value = WeatherModel(error = true)
        }
    }

}


