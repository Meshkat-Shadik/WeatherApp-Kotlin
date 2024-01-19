package com.example.weatherapp.presentation.di

import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.repository.datasource.WeatherDataSource
import com.example.weatherapp.data.repository.datasource_impl.WeatherDataSourceImpl
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository


    @Binds
    @Singleton
    abstract fun bindWeatherDataSource(
        weatherDataSourceImpl: WeatherDataSourceImpl
    ): WeatherDataSource

}