package com.myapp.weather.api

import com.myapp.weather.dto.WeatherForecast
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApiService {
    @GET("weather?q=Saint%20Petersburg&appid=d9e6fe2ca9bd114df14262b014663852&lang=ru")
    suspend fun getAll(): Response<WeatherForecast>
}
