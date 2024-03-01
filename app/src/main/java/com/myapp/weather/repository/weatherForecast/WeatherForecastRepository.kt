package com.myapp.weather.repository.weatherForecast

import com.myapp.weather.dto.daily.WeatherForecastDaily
import com.myapp.weather.dto.fiveDays.WeatherForecast5Days

interface WeatherForecastRepository {
    suspend fun getDailyForecastByName(name: String): WeatherForecastDaily
    suspend fun getForecast5DaysByName(name: String): WeatherForecast5Days

    suspend fun getDailyForecastByGPS(lat: Double, lon: Double): WeatherForecastDaily
    suspend fun getForecast5DaysByGPS(lat: Double, lon: Double): WeatherForecast5Days
}
