package com.myapp.weather.dto

data class WeatherForecast(
    val coord: Coordinates?,
    val weather: Weather?,
    val base: String,
    val main: WeatherData?,
    val visibility: Int,
    val wind: Wind?,
    val clouds: Clouds?,
    val dt: Long,
    val sys: Sys?,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)