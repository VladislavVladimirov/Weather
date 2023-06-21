package com.myapp.weather.model

import com.myapp.weather.dto.WeatherForecast

data class WeatherModel(
    val weatherForecast: WeatherForecast? = null,
    val loading: Boolean = false,
    val error: Boolean = false,
    val refreshing: Boolean = false,
)