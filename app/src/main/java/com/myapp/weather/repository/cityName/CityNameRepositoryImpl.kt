package com.myapp.weather.repository.cityName

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CityNameRepositoryImpl @Inject constructor(
    @ApplicationContext
    context: Context
) : CityNameRepository {

    private val gson = Gson()
    private val prefs = context.getSharedPreferences("repo2", Context.MODE_PRIVATE)
    private val type = TypeToken.getParameterized(String::class.java).type

    private val keyName = "name"
    private var name = ""
    private val dataName = MutableLiveData(name)

    init {
        prefs.getString(keyName, null)?.let {
            name = gson.fromJson(it, type)
            dataName.value = name
        }
    }

    override fun getName(): String {
        return name
    }

    override fun saveName(name: String) {
        this.name = name
        dataName.value = this.name
        sync()
    }

    private fun sync() {
        with(prefs.edit()) {
            putString(keyName, gson.toJson(name))
            apply()
        }
    }
}