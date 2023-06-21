package com.myapp.weather.repository

import com.myapp.weather.api.ApiWeather
import com.myapp.weather.dto.WeatherForecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl : Repository {
    override fun getAll(callback: Repository.Callback<WeatherForecast>) {
        ApiWeather.retrofitService.getAll().enqueue(object : Callback<WeatherForecast>{
            override fun onResponse(call: Call<WeatherForecast>, response: Response<WeatherForecast>) {
                if (!response.isSuccessful) {
                    callback.onError(Exception(response.message()))
                } else {
                    callback.onSuccess(requireNotNull(response.body()) { "body is null" })
                }
            }

            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                callback.onError(Exception(t))
            }
        })
    }
}