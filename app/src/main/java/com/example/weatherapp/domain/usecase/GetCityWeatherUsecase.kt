package com.example.weatherapp.domain.usecase

import arrow.core.Either
import com.example.weatherapp.data.dto.WeatherDto
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCityWeatherUsecase @Inject constructor
    (
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(city: String):Either<Exception,WeatherDto> = weatherRepository.getWeather(city)
}