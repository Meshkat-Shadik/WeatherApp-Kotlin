package com.example.weatherapp.features.new_weather.domain.entity

data class CoordEntity(val lon: Double, val lat: Double)
data class WeatherDetailEntity(val id: Int, val main: String, val description: String, val icon: String)
data class MainEntity(val temp: Double, val feelsLike: Double, val tempMin: Double, val tempMax: Double, val pressure: Int, val humidity: Int, val seaLevel: Int?, val grndLevel: Int?)
data class WindEntity(val speed: Double, val deg: Int)
data class RainEntity(val oneHour: Double?)
data class CloudsEntity(val all: Int)
data class SysEntity(val type: Int, val id: Int, val country: String, val sunrise: Long, val sunset: Long)

data class WeatherEntity(
    val coord: CoordEntity,
    val weather: List<WeatherDetailEntity>,
    val base: String,
    val main: MainEntity,
    val visibility: Int,
    val wind: WindEntity,
    val rain: RainEntity?,
    val clouds: CloudsEntity,
    val dt: Long,
    val sys: SysEntity,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)
