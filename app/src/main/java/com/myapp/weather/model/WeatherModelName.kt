package com.myapp.weather.model

import com.myapp.weather.dto.daily.WeatherForecastDaily
import com.myapp.weather.dto.fiveDays.WeatherForecast5Days

data class WeatherModelName(
    val weatherForecastDaily: WeatherForecastDaily? = null,
    val weatherForecast5Days: WeatherForecast5Days? = null,
    val loading: Boolean = false,
    val error: Boolean = false,
    val refreshing: Boolean = false,
)
