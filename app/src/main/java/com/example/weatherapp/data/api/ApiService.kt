package com.example.weatherapp.data.api

import com.example.weatherapp.data.dto.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")

    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): Response<WeatherDto>
}

//https://api.openweathermap.org/data/2.5/weather?q=Dhaka&appid=399f0e7c071a48a479b8a2dd1c56d296