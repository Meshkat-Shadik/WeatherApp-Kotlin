package com.example.weatherapp.data.repository.datasource_impl

import com.example.weatherapp.data.api.ApiService
import com.example.weatherapp.data.dto.Weather
import com.example.weatherapp.data.dto.WeatherDto
import com.example.weatherapp.data.repository.datasource.WeatherDataSource
import retrofit2.Response
import javax.inject.Inject


class WeatherDataSourceImpl @Inject constructor
    (
    private val apiKey: String,
    private val apiService: ApiService
): WeatherDataSource {
    override suspend fun getWeather(city: String): Response<WeatherDto> {
        return apiService.getWeather(city, apiKey)
    }

}