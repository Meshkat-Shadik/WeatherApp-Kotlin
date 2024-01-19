package com.example.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.UIState
import com.example.weatherapp.data.api.ApiService
import com.example.weatherapp.data.dto.WeatherDto
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.repository.datasource_impl.WeatherDataSourceImpl
import com.example.weatherapp.domain.usecase.GetCityWeatherUsecase
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.example.weatherapp.presentation.viewmodel.CityWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           val cityWeatherViewModel = hiltViewModel<CityWeatherViewModel>()
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CityWeatherScreen(cityWeatherViewModel)
                }
            }
        }
    }
}

@Composable
fun CityWeatherScreen(cityWeatherViewModel: CityWeatherViewModel) {
    // Collect the UI state from the view model
    val uiState by cityWeatherViewModel.uiState.collectAsState()

    // Composable UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display UI based on the state
        when (uiState) {
            is UIState.Idle -> {
                // Handle idle state if needed
            }
            is UIState.Loading -> {
                // Show loading state
                CircularProgressIndicator()
            }
            is UIState.Success -> {
                // Display weather data
                Text("Weather Data: ${(uiState as UIState.Success<WeatherDto>).data}")
            }
            is UIState.Failure -> {
                // Handle the error
                Text("Error: ${(uiState as UIState.Failure).exception}")
            }
        }
        //button to call api
        Button(onClick = {
            cityWeatherViewModel.fetchData()
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