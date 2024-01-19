package com.example.weatherapp.data.repository.datasource

import com.example.weatherapp.data.dto.WeatherDto
import retrofit2.Response

interface WeatherDataSource {
    suspend fun getWeather(city: String): Response<WeatherDto>
}