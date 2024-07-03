package com.example.weatherapp.features.new_weather.data.mappers

import com.example.weatherapp.features.new_weather.data.dto.*
import com.example.weatherapp.features.new_weather.domain.entity.*

 fun WeatherDto.toEntity(): WeatherEntity {
    return  WeatherEntity(
        coord = CoordEntity(lon = coordDto.lon, lat = coordDto.lat),
        weather = weatherDto.map { it.toEntity() },
        base = baseDto,
        main = mainDto.toEntity(),
        visibility = visibilityDto,
        wind = windDto.toEntity(),
        rain = rainDto?.toEntity(),
        clouds = cloudsDto.toEntity(),
        dt = dtDto,
        sys = sysDto.toEntity(),
        timezone = timezoneDto,
        id = idDto,
        name = nameDto,
        cod = codDto
    )
}
fun WeatherDetailDto.toEntity() = WeatherDetailEntity(
    id = id,
    main = main,
    description = description,
    icon = icon
)

fun MainDto.toEntity() = MainEntity(
    temp = temp,
    feelsLike = feelsLike,
    tempMin = tempMin,
    tempMax = tempMax,
    pressure = pressure,
    humidity = humidity,
    seaLevel = seaLevel,
    grndLevel = grndLevel
)

fun WindDto.toEntity() = WindEntity(
    speed = speed,
    deg = deg
)

fun RainDto.toEntity() = RainEntity(oneHour = oneHour)

fun CloudsDto.toEntity() = CloudsEntity(all = all)

fun SysDto.toEntity() = SysEntity(
    type = type,
    id = id,
    country = country,
    sunrise = sunrise,
    sunset = sunset
)


object WeatherMapper {
    fun fromDto(dto: WeatherDto): WeatherEntity {
        return dto.toEntity()
    }
}
