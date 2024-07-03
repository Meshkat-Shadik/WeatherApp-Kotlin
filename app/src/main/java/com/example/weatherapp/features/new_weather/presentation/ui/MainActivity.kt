package com.example.weatherapp.features.new_weather.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.core.wrapper.UIState
import com.example.weatherapp.features.new_weather.presentation.viewmodel.WeatherViewModel
import com.example.weatherapp.features.new_weather.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CityWeatherScreen()
                }
            }
        }
    }
}

@Composable
fun CityWeatherScreen() {
    val cityWeatherViewModel = hiltViewModel<WeatherViewModel>()

    // Collect the UI state from the view model
    val uiState by cityWeatherViewModel.uiState.collectAsState()

    //search query
    var citySearchQuery by remember { mutableStateOf("") }

    // Composable UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TextField to enter city name
        TextField(
            value = citySearchQuery,
            onValueChange = { citySearchQuery = it },
            placeholder = { Text("Enter City Name")},
            modifier = Modifier.fillMaxWidth(),
        )
        //  UI based on the state
        when (val currentState = uiState) {
            is UIState.Idle -> {}
            is UIState.Loading -> {CircularProgressIndicator()}
            is UIState.Success -> {
                Text("Weather Data: ${currentState.data}")
            }
            is UIState.Failure -> {
                Text("Error: ${currentState.failure.message}")
            }
        }
        //button to call api
        Button(onClick = {
            cityWeatherViewModel.fetchData(citySearchQuery)
        }) {
            Text("Get Weather")
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    WeatherAppTheme {
//        Greeting("Android")
//    }
//}