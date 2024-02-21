package com.myapp.weather.repository

import com.myapp.weather.dto.daily.WeatherForecastDaily
import com.myapp.weather.dto.fiveDays.WeatherForecast5Days

interface Repository {
    suspend fun getDailyForecast(): WeatherForecastDaily
    suspend fun getForecast5Days(): WeatherForecast5Days
}
