package com.myapp.weather.utils


import android.widget.ImageView
import com.myapp.weather.R
import com.myapp.weather.model.WeatherModel
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Date
import kotlin.math.roundToInt


object DataFormatter {


    fun getTime(input: Int?): String? {
        return if (input != null) {
            val formatter = SimpleDateFormat("HH:mm")
                formatter.format(Date((input * 1000L)))
        } else null

    }


    fun formatTemp(input: Double?): String? {
        return if (input != null) {
            ((input - 273.15).roundToInt()).toString() + "°"
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

    fun setWeatherIcon(input: ImageView, weather: String) {
        when (weather) {
            "Ясно" -> {
                val time = LocalTime.now().hour
                if (time in 7..20) {
                    input.setImageResource(R.drawable.ic_sun_128)
                } else {
                    input.setImageResource(R.drawable.ic_moon_128)
                }
            }

            "Пасмурно" -> {
                input.setImageResource(R.drawable.ic_cloud_128)
            }
        }

    }

    fun getVisibility(input: WeatherModel?): String? {
        return if (input != null) {
            return (input.weatherForecast?.visibility)?.div(1000).toString() + " км"
        } else {
            null
        }

    }


}