package com.myapp.weather.repository.cityName

interface CityNameRepository {
    fun getName(): String
    fun saveName(name: String)
}