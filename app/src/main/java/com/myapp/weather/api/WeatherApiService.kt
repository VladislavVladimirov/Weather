package com.myapp.weather.api

import com.myapp.weather.BuildConfig
import com.myapp.weather.dto.WeatherForecast
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import java.util.concurrent.TimeUnit



private const val BASE_URL = "${BuildConfig.BASE_URL}/weather?q=Saint Petersburg&appid=d9e6fe2ca9bd114df14262b014663852&lang=ru"

interface WeatherApiService {
    @GET
    fun getAll(): Call<WeatherForecast>
}

private val logging = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
private val client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(logging)
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .baseUrl(BASE_URL)
    .build()
object ApiWeather {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create()
    }
}