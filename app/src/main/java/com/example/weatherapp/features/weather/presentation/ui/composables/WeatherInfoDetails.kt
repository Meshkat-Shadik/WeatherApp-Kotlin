package com.example.weatherapp.features.weather.presentation.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.weatherapp.core.utils.DateTimeUtils
import com.example.weatherapp.features.weather.domain.entity.WeatherEntity

@Composable
fun WeatherInfoDetails(weather: WeatherEntity) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "${weather.name}, ${weather.sys.country}",
            style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
        )
        Text(
            text = "${weather.weather.first().main}, ${
                DateTimeUtils.convertUnixTimeToDateTime(
                    weather.dt
                )
            }",
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
        )
        Text(
            text = "${weather.main.temp}°",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Color.White,
                fontSize = 64.sp
            )
        )
        Text(
            text = "Feels Like: ${weather.main.feelsLike}°",
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
        )
    }
}