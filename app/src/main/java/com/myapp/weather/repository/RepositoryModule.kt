package com.myapp.weather.repository


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
    fun bindsRepository(impl: RepositoryImpl): Repository
}