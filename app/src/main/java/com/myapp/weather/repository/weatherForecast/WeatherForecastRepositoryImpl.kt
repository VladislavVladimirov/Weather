package com.myapp.weather.repository.weatherForecast


import com.myapp.weather.api.WeatherApiService
import com.myapp.weather.dto.daily.WeatherForecastDaily
import com.myapp.weather.dto.fiveDays.WeatherForecast5Days
import com.myapp.weather.error.ApiError
import com.myapp.weather.error.NetworkError
import com.myapp.weather.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
) : WeatherForecastRepository {

    override suspend fun getDailyForecastByName(name:String): WeatherForecastDaily {
        try {
            val response = apiService.getDailyForecastByName(name)
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e:IOException){
            e.printStackTrace()
            throw NetworkError
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownError
        }
    }

    override suspend fun getForecast5DaysByName(name: String): WeatherForecast5Days {
        try {
            val response = apiService.getForecast5DaysByName(name)
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e:IOException){
            e.printStackTrace()
            throw NetworkError
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownError
        }
    }

    override suspend fun getDailyForecastByGPS(lat: Double, lon: Double): WeatherForecastDaily {
        try {
            val response = apiService.getDailyForecastByGps(lat,lon)
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e:IOException){
            e.printStackTrace()
            throw NetworkError
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownError
        }
    }

    override suspend fun getForecast5DaysByGPS(lat: Double, lon: Double): WeatherForecast5Days {
        try {
            val response = apiService.getForecast5DaysByGps(lat,lon)
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e:IOException){
            e.printStackTrace()
            throw NetworkError
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownError
        }
    }


}