package com.myapp.weather.utils


import com.myapp.weather.model.WeatherModel
import java.text.SimpleDateFormat
import java.util.Date


object DataFormatter {

    fun getDate(): String {
        val dateFormat = SimpleDateFormat("HH:mm ")
        return dateFormat.format(Date())
    }

    fun formatTemp(input: Double?): String? {
        return if (input != null) {
            (Math.round(input - 273.15)).toString() + "°"
        } else null
    }

    fun getDescription(input: WeatherModel?): String? {
        return if (input != null) {
            val weather = input.weatherForecast?.weather?.first { it.id in 200..804 }
            return weather?.description?.replaceFirstChar(Char::titlecase)
        } else null
    }

    fun getHumidity(input: WeatherModel?): String? {
        return if (input != null) {
            return input.weatherForecast?.main?.humidity.toString() + "%"
        } else null
    }

    fun getPressure(input: WeatherModel?): String? {
        return if (input != null) {
            return input.weatherForecast?.main?.pressure.toString() + " мбар"
        } else null
    }

    fun getWind(input: WeatherModel?): String? {
        val directions = arrayOf("С", "CВ", "В", "ЮВ", "Ю", "ЮЗ", "З", "CЗ")
        val degreesInput = input?.weatherForecast?.wind?.deg
        var degrees = degreesInput?.times(8)?.div(360)
        degrees = degrees?.plus(8)?.rem(8)
        return if ((input != null) && (degrees != null)) {
            return input.weatherForecast?.wind?.speed.toString() + " м/с" + " ${directions[degrees]}"
        } else null
    }

    fun getIconId(input: WeatherModel?): String? {
        return if (input != null) {
            val weather = input.weatherForecast?.weather?.first { it.id in 200..804 }
            return weather?.icon
        } else null
    }


}