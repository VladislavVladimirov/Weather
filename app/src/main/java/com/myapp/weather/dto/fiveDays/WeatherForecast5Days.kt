package com.myapp.weather.dto.fiveDays

data class WeatherForecast5Days(
    val city: City? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    val list: ArrayList<WeatherForecastItem> = arrayListOf(),
    val message: Int? = null
)










