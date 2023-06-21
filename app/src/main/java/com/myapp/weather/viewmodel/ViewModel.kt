package com.myapp.weather.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myapp.weather.repository.Repository
import com.myapp.weather.repository.RepositoryImpl
import com.myapp.weather.dto.WeatherForecast
import com.myapp.weather.model.WeatherModel


class ViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository = RepositoryImpl()
    private val _data = MutableLiveData(WeatherModel())
    val data: LiveData<WeatherModel>
        get() = _data
    init {
        loadWeather()
    }
  fun loadWeather() {
        _data.postValue(_data.value?.copy(loading = true))
        repository.getAll(object : Repository.Callback<WeatherForecast> {
            override fun onSuccess(input: WeatherForecast) {
                _data.postValue(WeatherModel(weatherForecast = input))
            }
            override fun onError(e: Exception) {
                _data.postValue(WeatherModel(error = true))
            }
        })
    }
}
