package com.example.weatherapp.features.weather.domain.use_case

import arrow.core.Either
import com.example.weatherapp.core.failure.AppFailure
import com.example.weatherapp.core.use_case.UseCase
import com.example.weatherapp.features.weather.domain.entity.WeatherEntity
import com.example.weatherapp.features.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeather @Inject constructor(
    private val repository: WeatherRepository
) : UseCase<WeatherEntity, String> {
    override suspend fun invoke(params: String): Either<AppFailure, WeatherEntity> {
        return repository.getWeather(params)
    }
}