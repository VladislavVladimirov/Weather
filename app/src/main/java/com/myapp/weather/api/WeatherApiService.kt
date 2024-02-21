package com.myapp.weather.api

import com.myapp.weather.dto.daily.WeatherForecastDaily
import com.myapp.weather.dto.fiveDays.WeatherForecast5Days
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApiService {
    @GET("weather?q=Saint%20Petersburg&appid=d9e6fe2ca9bd114df14262b014663852&lang=ru&units=metric")
    suspend fun getDailyForecast(): Response<WeatherForecastDaily>
    @GET("forecast?q=Saint Petersburg&appid=d9e6fe2ca9bd114df14262b014663852&lang=ru&units=metric")
    suspend fun getForecast5Days(): Response<WeatherForecast5Days>
}
