package com.example.weatherapp.domain.repository

import arrow.core.Either
import com.example.weatherapp.data.dto.WeatherDto

interface WeatherRepository {
    suspend fun getWeather(city:String): Either<Exception, WeatherDto>
}