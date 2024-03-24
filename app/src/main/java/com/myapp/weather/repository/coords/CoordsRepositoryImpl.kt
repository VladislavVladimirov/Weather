package com.myapp.weather.repository.coords

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CoordsRepositoryImpl @Inject constructor(
    @ApplicationContext
    context: Context
) : CoordsRepository {
    private val gson = Gson()
    private val prefs = context.getSharedPreferences("repo1", Context.MODE_PRIVATE)
    private val type = TypeToken.getParameterized(String::class.java).type

    private val keyLat = "lat"
    private val keyLon = "lon"
    private var lat = ""
    private var lon = ""
    private val dataLat = MutableLiveData(lat)
    private val dataLon = MutableLiveData(lon)

    init {
        prefs.getString(keyLat, null)?.let {
            lat = gson.fromJson(it, type)
            dataLat.value = lat
        }
        prefs.getString(keyLon, null)?.let {
            lon = gson.fromJson(it, type)
            dataLon.value = lon
        }
    }

    override fun getLon(): String {
        return lon
    }

    override fun getLat(): String {
        return lat
    }

    override fun saveLat(lat: String) {
        this.lat = lat
        dataLat.value = this.lat
        sync()
    }

    override fun saveLon(lon: String) {
        this.lon = lon
        dataLon.value = this.lon
        sync()
    }

    private fun sync() {
        with(prefs.edit()) {
            putString(keyLat, gson.toJson(lat))
            putString(keyLon, gson.toJson(lon))
            apply()
        }
    }
}