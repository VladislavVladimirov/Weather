package com.myapp.weather.repository

import com.myapp.weather.dto.WeatherForecast

interface Repository {
    suspend fun getAll():WeatherForecast
}
