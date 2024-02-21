package com.myapp.weather.dto.fiveDays

import com.myapp.weather.dto.daily.Clouds
import com.myapp.weather.dto.daily.Weather

data class WeatherForecastItem(
    val clouds: Clouds = Clouds(),
    val dt: Int? = null,
    val dt_txt: String? = null,
    val main: MainInfo = MainInfo(),
    val pop: Double? = null,
    val rain: Rain = Rain(),
    val snow: Snow = Snow(),
    val sys: Sys5 = Sys5(),
    val visibility: Int? = null,
    val weather: ArrayList<Weather> = arrayListOf(),
    val wind: Wind5 = Wind5()
)


