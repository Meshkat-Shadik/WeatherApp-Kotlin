package com.example.weatherapp.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.weatherapp.data.dto.WeatherDto
import com.example.weatherapp.data.repository.datasource.WeatherDataSource
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor
    (
    private  val apiKey: String,
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {
    override suspend fun getWeather(city: String): Either<Exception, WeatherDto> {
        return try {
            val response = weatherDataSource.getWeather(city)
            if (response.isSuccessful) {
                response.body()?.right() ?: Exception("Body is empty").left()
            } else {
                Exception("Response is not successful").left()
            }
        } catch (e: Exception) {
            return e.left()
        }
    }
}