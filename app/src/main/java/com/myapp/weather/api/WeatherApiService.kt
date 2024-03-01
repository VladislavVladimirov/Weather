package com.myapp.weather.api

import com.myapp.weather.dto.daily.WeatherForecastDaily
import com.myapp.weather.dto.fiveDays.WeatherForecast5Days
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {
    @GET("weather?appid=d9e6fe2ca9bd114df14262b014663852&lang=ru&units=metric")
    suspend fun getDailyForecastByName(@Query("q") name: String): Response<WeatherForecastDaily>

    @GET("forecast?appid=d9e6fe2ca9bd114df14262b014663852&lang=ru&units=metric")
    suspend fun getForecast5DaysByName(@Query("q") name: String): Response<WeatherForecast5Days>

    @GET("weather?appid=d9e6fe2ca9bd114df14262b014663852&lang=ru&units=metric")
    suspend fun getDailyForecastByGps(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<WeatherForecastDaily>

    @GET("forecast?&appid=d9e6fe2ca9bd114df14262b014663852&lang=ru&units=metric")
    suspend fun getForecast5DaysByGps(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<WeatherForecast5Days>

}


