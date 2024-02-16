package com.myapp.weather.dto

data class Main(
    val temp: Double? = null,
    val feelsLike: String? = null,
    val tempMin: Double? = null,
    val tempMax: Double? = null,
    val pressure: Int? = null,
    val humidity: Int? = null
)