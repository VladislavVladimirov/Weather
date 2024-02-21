package com.myapp.weather.dto.daily


data class WeatherForecastDaily(
    val coord: Coord? = Coord(),
    val weather: ArrayList<Weather> = arrayListOf(),
    val base: String? = null,
    val main: Main? = Main(),
    val visibility: Int? = null,
    val wind: Wind? = Wind(),
    val clouds: Clouds? = Clouds(),
    val dt: Int? = null,
    val sys: Sys? = Sys(),
    val timezone: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val cod: Int? = null
)