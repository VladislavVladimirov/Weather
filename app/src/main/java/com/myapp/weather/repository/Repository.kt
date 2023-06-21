package com.myapp.weather.repository

import com.myapp.weather.dto.WeatherForecast
import java.lang.Exception

interface Repository {
    fun getAll(callback: Callback<WeatherForecast>)
    interface Callback <T> {
        fun onSuccess(input: T) {}
        fun onError(e:Exception){}
    }
}
