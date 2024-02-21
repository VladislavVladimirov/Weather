package com.myapp.weather.utils


import android.widget.ImageView
import com.myapp.weather.R
import com.myapp.weather.model.WeatherModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlin.math.roundToInt


object DataFormatter {


    fun getTime(input: Int?): String? {
        return if (input != null) {
            val formatter = SimpleDateFormat("HH:mm")
            formatter.format(Date((input * 1000L)))
        } else null

    }
    fun getDayOfWeek(input: String?): String? {
        return if (input != null) {
            val date = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            when (date.dayOfWeek.toString()) {
                "MONDAY" -> {return "Понедельник"}
                "TUESDAY" -> {return "Вторник"}
                "WEDNESDAY" -> {return "Среда"}
                "THURSDAY" -> {return "Четверг"}
                "FRIDAY" -> {return "Пятница"}
                "SATURDAY" -> {return "Суббота"}
                "SUNDAY" -> {return "Воскресенье"}
                else -> {return null}
            }
        } else null
        }



    fun formatTemp(input: Double?): String? {
        return if (input != null) {
            (input.roundToInt()).toString() + "°"
        } else null
    }


    fun getDescription(input: WeatherModel?): String? {
        return if (input != null) {
            val weather = input.weatherForecastDaily?.weather?.first { it.id in 200..804 }
            return weather?.description?.replaceFirstChar(Char::titlecase)
        } else null
    }

    fun getHumidity(input: WeatherModel?): String? {
        return if (input != null) {
            return input.weatherForecastDaily?.main?.humidity.toString() + "%"
        } else null
    }

    fun getPressure(input: WeatherModel?): String? {
        return if (input != null) {
            return input.weatherForecastDaily?.main?.pressure.toString() + " мбар"
        } else null
    }

    fun getWind(input: WeatherModel?): String? {
        val directions = arrayOf("С", "CВ", "В", "ЮВ", "Ю", "ЮЗ", "З", "CЗ")
        val degreesInput = input?.weatherForecastDaily?.wind?.deg
        var degrees = degreesInput?.times(8)?.div(360)
        degrees = degrees?.plus(8)?.rem(8)
        return if ((input != null) && (degrees != null)) {
            return input.weatherForecastDaily?.wind?.speed.toString() + " м/с" + " ${directions[degrees]}"
        } else null
    }


    fun getIconId(input: WeatherModel?): String? {
        return if (input != null) {
            val weather = input.weatherForecastDaily?.weather?.first { it.id in 200..804 }
            return weather?.icon
        } else null
    }

    fun setWeatherIcon(input: ImageView, weather: String) {
        when (weather) {
            "ясно" -> {
                val time = LocalTime.now().hour
                if (time in 7..20) {
                    input.setImageResource(R.drawable.ic_sun_32)
                } else {
                    input.setImageResource(R.drawable.ic_moon_32)
                }
            }

            "пасмурно" -> {
                input.setImageResource(R.drawable.ic_cloud_32)
            }

            "облачно с прояснениями" -> {
                val time = LocalTime.now().hour
                if (time in 7..20) {
                    input.setImageResource(R.drawable.ic_sun_with_clouds_32)
                } else {
                    input.setImageResource(R.drawable.ic_moon_and_clouds_32)
                }
            }

            "небольшой снег" -> {
                input.setImageResource(R.drawable.ic_little_snow_32)
            }

            "небольшой дождь" -> {
                input.setImageResource(R.drawable.ic_little_rain_32)
            }
        }

    }

    fun getVisibility(input: WeatherModel?): String? {
        return if (input != null) {
            return (input.weatherForecastDaily?.visibility)?.div(1000).toString() + " км"
        } else {
            null
        }

    }


}