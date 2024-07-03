package com.example.weatherapp.features.new_weather.domain.repository

import arrow.core.Either
import com.example.weatherapp.core.failure.AppFailure
import com.example.weatherapp.features.new_weather.domain.entity.WeatherEntity

interface  WeatherRepository {
    suspend fun getWeather(cityName: String): Either<AppFailure,WeatherEntity>
}