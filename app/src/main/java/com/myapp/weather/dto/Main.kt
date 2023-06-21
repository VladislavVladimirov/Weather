package com.myapp.weather.dto

data class Main (
    val temp: Double? = null,
    val feelsLike: Double? = null,
    val tempMin: Double? = null,
    val tempMax: Double? = null,
    val pressure: Int? = null,
    val humidity: Int? = null
        )