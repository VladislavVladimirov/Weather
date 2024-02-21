package com.myapp.weather.dto.fiveDays

import com.myapp.weather.dto.daily.Coord

data class City(
    val coord: Coord = Coord(),
    val country: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val population: Int? = null,
    val sunrise: Int? = null,
    val sunset: Int? = null,
    val timezone: Int? = null
)