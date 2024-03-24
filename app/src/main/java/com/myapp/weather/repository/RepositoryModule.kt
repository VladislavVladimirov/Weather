package com.myapp.weather.repository


import com.myapp.weather.repository.cityName.CityNameRepository
import com.myapp.weather.repository.cityName.CityNameRepositoryImpl
import com.myapp.weather.repository.coords.CoordsRepository
import com.myapp.weather.repository.coords.CoordsRepositoryImpl
import com.myapp.weather.repository.weatherForecast.WeatherForecastRepository
import com.myapp.weather.repository.weatherForecast.WeatherForecastRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsWeatherForecastRepository(impl: WeatherForecastRepositoryImpl): WeatherForecastRepository

    @Singleton
    @Binds
    fun bindsCoordsRepository(impl: CoordsRepositoryImpl): CoordsRepository

    @Singleton
    @Binds
    fun bindsCityNameRepository(impl: CityNameRepositoryImpl): CityNameRepository
}