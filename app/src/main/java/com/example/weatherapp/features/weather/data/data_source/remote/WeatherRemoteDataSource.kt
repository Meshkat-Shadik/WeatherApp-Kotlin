package com.example.weatherapp.features.weather.data.data_source.remote


//Retrofit REST API for Weather

import com.example.weatherapp.features.weather.data.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteDataSource {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): WeatherDto
}