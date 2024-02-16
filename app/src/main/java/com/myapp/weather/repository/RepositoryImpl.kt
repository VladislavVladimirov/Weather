package com.myapp.weather.repository


import com.myapp.weather.api.WeatherApiService
import com.myapp.weather.dto.WeatherForecast
import com.myapp.weather.error.ApiError
import com.myapp.weather.error.NetworkError
import com.myapp.weather.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
) : Repository {

    override suspend fun getAll(): WeatherForecast {
        try {
            val response = apiService.getAll()
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e:IOException){
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}