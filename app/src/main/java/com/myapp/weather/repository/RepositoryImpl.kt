package com.myapp.weather.repository


import com.myapp.weather.api.WeatherApiService
import com.myapp.weather.dto.daily.WeatherForecastDaily
import com.myapp.weather.dto.fiveDays.WeatherForecast5Days
import com.myapp.weather.error.ApiError
import com.myapp.weather.error.NetworkError
import com.myapp.weather.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
) : Repository {

    override suspend fun getDailyForecast(): WeatherForecastDaily {
        try {
            val response = apiService.getDailyForecast()
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e:IOException){
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }

    override suspend fun getForecast5Days(): WeatherForecast5Days {
        try {
            val response = apiService.getForecast5Days()
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e:IOException){
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}