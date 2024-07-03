package com.example.weatherapp.features.new_weather.data.dto

import com.google.gson.annotations.SerializedName

data class CoordDto(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)
data class WeatherDetailDto(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)



data class MainDto(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("sea_level") val seaLevel: Int?,
    @SerializedName("grnd_level") val grndLevel: Int?
)

data class WindDto(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int
)

data class RainDto(
    @SerializedName("1h") val oneHour: Double?
)

data class CloudsDto(
    @SerializedName("all") val all: Int
)

data class SysDto(
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)

data class WeatherDto(
    @SerializedName("coord") val coordDto: CoordDto,
    @SerializedName("weather") val weatherDto: List<WeatherDetailDto>,
    @SerializedName("base") val baseDto: String,
    @SerializedName("main") val mainDto: MainDto,
    @SerializedName("visibility") val visibilityDto: Int,
    @SerializedName("wind") val windDto: WindDto,
    @SerializedName("rain") val rainDto: RainDto?,
    @SerializedName("clouds") val cloudsDto: CloudsDto,
    @SerializedName("dt") val dtDto: Long,
    @SerializedName("sys") val sysDto: SysDto,
    @SerializedName("timezone") val timezoneDto: Int,
    @SerializedName("id") val idDto: Long,
    @SerializedName("name") val nameDto: String,
    @SerializedName("cod") val codDto: Int
)