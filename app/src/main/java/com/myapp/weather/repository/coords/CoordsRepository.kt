package com.myapp.weather.repository.coords

interface CoordsRepository {
    fun getLat(): String
    fun saveLat(lat: String)
    fun getLon(): String
    fun saveLon(lon: String)
}