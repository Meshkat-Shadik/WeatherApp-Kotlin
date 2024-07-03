package com.example.weatherapp.features.weather.data.repository

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import com.example.weatherapp.core.failure.AppFailure
import com.example.weatherapp.core.failure.FailureMapper
import com.example.weatherapp.features.weather.data.data_source.remote.WeatherRemoteDataSource
import com.example.weatherapp.features.weather.data.mappers.toEntity
import com.example.weatherapp.features.weather.domain.entity.WeatherEntity
import com.example.weatherapp.features.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dataSource: WeatherRemoteDataSource,
    private val apiKey: String
) : WeatherRepository {
    override suspend fun getWeather(cityName: String): Either<AppFailure, WeatherEntity> {
        return try {
            val response = dataSource.getWeather(cityName, apiKey)
            return Right(response.toEntity())
        } catch (e: Exception) {
            Left(FailureMapper.getFailure(e))
        }
    }
}
